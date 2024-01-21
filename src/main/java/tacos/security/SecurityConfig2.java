package tacos.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.server.SecurityWebFilterChain;
import reactor.core.publisher.Mono;
import tacos.AppUser;
//import tacos.data.UserRepository;
import tacos.data.UserRepository2;
import org.springframework.security.core.userdetails.User;


@Configuration
@EnableWebFluxSecurity
public class SecurityConfig2 {

    private final UserRepository2 userRepo;

    public SecurityConfig2(UserRepository2 userRepo) {
        this.userRepo = userRepo;
    }

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        return http
            .authorizeExchange()
            .pathMatchers("/api/tacos", "/orders").hasAuthority("ROLE_USER")
            .anyExchange().permitAll()
            .and()
            .build();
    }

    @Bean
    public ReactiveUserDetailsService userDetailsService(UserRepository2 userRepo) {
        return username -> userRepo.findByUsername(username)
                .map(appUser -> User.builder()
                        .username(appUser.getUsername())
                        .password(appUser.getPassword())
                        .authorities(appUser.getAuthorities())
                        .accountExpired(!appUser.isAccountNonExpired())
                        .accountLocked(!appUser.isAccountNonLocked())
                        .credentialsExpired(!appUser.isCredentialsNonExpired())
                        .disabled(!appUser.isEnabled())
                        .build())
                .switchIfEmpty(Mono.error(new UsernameNotFoundException("User not found: " + username)));
    }





}
