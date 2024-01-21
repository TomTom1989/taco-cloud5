package tacos.data;

import java.awt.print.Pageable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import jakarta.validation.Valid;
import reactor.core.publisher.Mono;
import tacos.Taco;
import tacos.TacoOrder;

public interface TacoRepository extends ReactiveCrudRepository<Taco, Long> {
    List<Taco> findByTacoOrder(TacoOrder tacoOrder);

	Mono<@Valid Taco> save(@Valid Taco taco);

	// save(@Valid Taco taco);
}

