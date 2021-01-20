package com.vartdalen.imagestoreweb.configuration.authentication;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import reactor.core.publisher.Mono;

@Component
public class AuthenticationManager implements ReactiveAuthenticationManager {

    @Value("${server.api.authorization.token}")
    private String AUTHORIZATION_TOKEN;

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        String token = (String) authentication.getCredentials();
        if (token.equals(AUTHORIZATION_TOKEN)) {
            authentication.setAuthenticated(true);
            return Mono.just(authentication);
        }
        return Mono.empty();
    }
}