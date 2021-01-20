package com.vartdalen.imagestoreweb.configuration;

import com.vartdalen.imagestoreweb.configuration.authentication.SecurityContextRepository;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
public class SecurityConfiguration {

    private SecurityContextRepository securityContextRepository;

    public SecurityConfiguration(SecurityContextRepository securityContextRepository) {
        this.securityContextRepository = securityContextRepository;
    }

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http
            .httpBasic().disable()
            .formLogin().disable()
            .logout().disable();

        http
            .securityContextRepository(securityContextRepository)
            .authorizeExchange()
            .pathMatchers(HttpMethod.POST, "/api/**").authenticated()
            .pathMatchers(HttpMethod.PUT, "/api/**").authenticated()
            .pathMatchers(HttpMethod.DELETE, "/api/**").authenticated()
            .anyExchange().permitAll()
            .and()
            .csrf().disable();

        return http.build();
    }
}
