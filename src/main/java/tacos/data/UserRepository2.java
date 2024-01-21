package tacos.data;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;
import tacos.AppUser;

public interface UserRepository2 extends ReactiveCrudRepository<AppUser, Long> {
	Mono<AppUser> findByUsername(String username);

	void save(Mono<AppUser> appUser);
}
