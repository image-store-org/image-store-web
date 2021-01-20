package com.vartdalen.imagestoreweb.configuration.authentication;

import org.apache.commons.lang3.NotImplementedException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Component
public class SecurityContextRepository implements ServerSecurityContextRepository {

    private AuthenticationManager authenticationManager;

    public SecurityContextRepository(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    public Mono<Void> save(ServerWebExchange exchange, SecurityContext context) {
        throw new NotImplementedException("Not implemented");
    }

    @Override
    public Mono<SecurityContext> load(ServerWebExchange exchange) {
        ServerHttpRequest request = exchange.getRequest();
        String token = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        if (token != null && !token.isEmpty()) {
            Authentication auth = new ApiAuthenticationToken(token);
            return this.authenticationManager.authenticate(auth)
                .map((authentication) -> new SecurityContextImpl(authentication));
        } else {
            return Mono.empty();
        }
    }
}