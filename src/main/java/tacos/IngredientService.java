package tacos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class IngredientService {
    private final IngredientRepository ingredientRepository;

    @Autowired
    public IngredientService(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    public Mono<Ingredient> saveIngredient(Ingredient ingredient) {
        return ingredientRepository.save(ingredient);
    }

    public Flux<Ingredient> getAllIngredients() {
        return ingredientRepository.findAll();
    }
}

