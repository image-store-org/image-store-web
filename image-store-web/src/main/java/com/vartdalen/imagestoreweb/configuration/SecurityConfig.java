package com.vartdalen.imagestoreweb.configuration;

import com.vartdalen.imagestoreweb.filter.ApiAuthorizationFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${server.api.authorization.header}")
    private String authorizationHeader;

    @Value("${server.api.authorization.token}")
    private String authorizationToken;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        ApiAuthorizationFilter filter = new ApiAuthorizationFilter(authorizationHeader);
        filter.setAuthenticationManager(authentication -> {
            String principal = (String) authentication.getPrincipal();
            if (!authorizationToken.equals(principal))
            {
                throw new BadCredentialsException("The API key was not found or not the expected value.");
            }
            authentication.setAuthenticated(true);
            return authentication;
        });

        http
                .requiresChannel()
                .anyRequest()
                .requiresSecure();
        http
                .antMatcher("/api/**")
                .csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS).
                and()
                .addFilter(filter)
                .authorizeRequests()
                .anyRequest()
                .authenticated();
    }
}
