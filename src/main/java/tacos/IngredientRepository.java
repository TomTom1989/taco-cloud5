package tacos;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;


public interface IngredientRepository extends ReactiveCrudRepository<Ingredient, String> {
}