package tacos.data;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import reactor.core.publisher.Flux;
import tacos.Ingredient;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

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
	/* @Bean
	    public CommandLineRunner run(IngredientService ingredientService) {
	        return args -> {
	            ingredientService.findAllIngredients();
	        };
	    }*/
	
	public Ingredient getIngredientById(String ingredientId) {
	    return webClient
	            .get()
	            .uri("/ingredients/{id}", ingredientId)
	            .retrieve()
	            .bodyToMono(Ingredient.class)
	            .block(); // This converts Mono<Ingredient> to Ingredient
	}


// Get all ingredients in Reactive way
	/*public void findAllIngredients() {
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
	}*/
	
	public void createIngredient() {
	    Ingredient newIngredient = new Ingredient("Ingredient C", Ingredient.Type.WRAP, null, "FLTO" );

	    Mono<Ingredient> ingredientMono = Mono.just(newIngredient);

	    WebClient.create()
	    .post()
	    .uri("http://localhost:9000/ingredients")
	    .body(ingredientMono, Ingredient.class)
	    .retrieve()
	    .bodyToMono(Ingredient.class)
	    .timeout(Duration.ofSeconds(10))
	    .subscribe(
	        ingredient -> System.out.println("Created ingredient: " + ingredient),
	        error -> {
	            if (error instanceof WebClientResponseException) {
	                WebClientResponseException responseException = (WebClientResponseException) error;
	                System.err.println("Error occurred: " + responseException.getStatusCode());
	                System.err.println("Error body: " + responseException.getResponseBodyAsString());
	            } else {
	                System.err.println("Error occurred: " + error.getMessage());
	            }
	        },
	        () -> System.out.println("Ingredient creation process completed.")
	    );

	}
	
	
	 @Bean
	    public CommandLineRunner run2(IngredientService ingredientService) {
	        return args -> {
	            ingredientService.createIngredient();
	        };
	    }
	
	
	 public void deleteIngredient(String ingredientId) {
		    WebClient.create()
		        .delete()
		        .uri("http://localhost:9000/ingredients/{id}", ingredientId)
		        .retrieve()
		        .bodyToMono(Void.class) // Typically, DELETE doesn't return a body
		        .timeout(Duration.ofSeconds(10))
		        .subscribe(
		            success -> System.out.println("Deleted ingredient with ID: " + ingredientId),
		            error -> {
		                if (error instanceof WebClientResponseException) {
		                    WebClientResponseException responseException = (WebClientResponseException) error;
		                    System.err.println("Error occurred: " + responseException.getStatusCode());
		                    System.err.println("Error body: " + responseException.getResponseBodyAsString());
		                } else {
		                    System.err.println("Error occurred: " + error.getMessage());
		                }
		            },
		            () -> System.out.println("Ingredient deletion process completed.")
		        );
		}

	
	
	 @Bean
	    public CommandLineRunner run3(IngredientService ingredientService) {
	        return args -> {
	            ingredientService.deleteIngredient("COTO");
	        };
	    }
	
	
	
	
	
	
	
	
	
	
	
	
	/*public Ingredient createIngredient() {
	    Ingredient newIngredient = new Ingredient("INGC", "Ingredient C", Ingredient.Type.VEGGIES);

	    return webClient.post()
	            .uri("/ingredients/")
	            .body(Mono.just(newIngredient), Ingredient.class)
	            .retrieve()
	            .bodyToMono(Ingredient.class)
	            .block(); // This converts Mono<Ingredient> to Ingredient, making the call synchronous
	}
*/


    
    }