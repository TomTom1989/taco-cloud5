package tacos.data;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import reactor.core.publisher.Flux;
import tacos.Ingredient;
import org.springframework.web.reactive.function.client.WebClient;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import reactor.core.publisher.Flux;
import tacos.Ingredient;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.databind.ObjectMapper;

import aj.org.objectweb.asm.Type;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

//import org.json.JSONArray;
//import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tacos.AppUser;
import tacos.Ingredient;
import tacos.IngredientData;

import org.springframework.security.core.Authentication;




@Service
public class IngredientService {
	
	@Autowired
    private UserRepository userRepository;
	
	@Autowired
    private IngredientRepository ingredientRepository;
	
	@Autowired
    private AppUserService appUserService;
	
	@Autowired
    private WebClient webClient;

	
	/*public Ingredient createIngredient(IngredientData ingredientData) {
	    AppUser user = userRepository.findByUsername(ingredientData.getUsername());
	    if (user != null) {
	        Long appUserId = user.getId();
	        Ingredient ingredient = new Ingredient(ingredientData.getId(), ingredientData.getName(), ingredientData.getType(), appUserId);
	        return ingredientRepository.save(ingredient);
	    } else {
	        // Handle the scenario where the user is not found
	        return null;
	    }
	}*/
	 @Bean
	    public CommandLineRunner run(IngredientService ingredientService) {
	        return args -> {
	            ingredientService.findAllIngredients();
	        };
	    }
	
	public Ingredient getIngredientById(String ingredientId) {
	    return webClient
	            .get()
	            .uri("/ingredients/{id}", ingredientId)
	            .retrieve()
	            .bodyToMono(Ingredient.class)
	            .block(); // This converts Mono<Ingredient> to Ingredient
	}

	// Reactive way using Flux  1
	/*public void findAllIngredients() {
	    Flux<Ingredient> ingredients = WebClient.create()
	            .get()
	            .uri("http://localhost:9000/ingredients")
	            .retrieve()
	            .bodyToFlux(Ingredient.class);

	    ingredients.doOnNext(ingredient -> System.out.println("Processing ingredient: " + ingredient))
	    .doOnError(error -> System.err.println("Error in stream: " + error.getMessage()))
	    .doOnComplete(() -> System.out.println("Stream completed"))
	    .subscribe(
	        ingredient -> {
	            // Action to perform on each Ingredient
	            System.out.println("Received ingredient: " + ingredient);
	        },
	        error -> {
	            // Action to perform on error
	            System.err.println("Error occurred: " + error.getMessage());
	        },
	        () -> {
	            // Action to perform on completion of the stream
	            System.out.println("All ingredients processed.");
	        }
	    );
	}*/
//2
	/*public Flux<Ingredient> findAllIngredients() {
	    return WebClient.create()
	            .get()
	            .uri("http://localhost:9000/ingredients")
	            .retrieve()
	            .bodyToFlux(Ingredient.class);
	}*/


	public void findAllIngredients() {
	    WebClient.create()
	        .get()
	        .uri("http://localhost:9000/ingredients")
	        .retrieve()
	        .bodyToMono(JsonNode.class)
	        .flatMapMany(jsonNode -> {
	            JsonNode ingredientsNode = jsonNode.path("_embedded").path("ingredients");
	            ObjectMapper mapper = new ObjectMapper();
	            return Flux.fromIterable(ingredientsNode)
	                       .map(node -> mapper.convertValue(node, Ingredient.class));
	        })
	        .timeout(Duration.ofSeconds(10)) // Set the timeout duration to 10 seconds
	        .subscribe(
	            ingredient -> System.out.println("Received ingredient: " + ingredient),
	            error -> {
	                if (error instanceof java.util.concurrent.TimeoutException) {
	                    System.err.println("Request timed out.");
	                } else {
	                    System.err.println("Error occurred: " + error.getMessage());
	                }
	            },
	            () -> System.out.println("All ingredients processed.")
	        );
	}
	
	
	
	
	
	
	public Ingredient createIngredient() {
	    Ingredient newIngredient = new Ingredient("INGC", "Ingredient C", Ingredient.Type.VEGGIES);

	    return webClient.post()
	            .uri("/ingredients/create")
	            .body(Mono.just(newIngredient), Ingredient.class)
	            .retrieve()
	            .bodyToMono(Ingredient.class)
	            .block(); // This converts Mono<Ingredient> to Ingredient, making the call synchronous
	}



    
    }