package com.vartdalen.imagestoreweb.configuration;

import com.vartdalen.imagestoreweb.filter.ApiAuthorizationFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${server.api.authorization.header}")
    private String AUTHORIZATION_HEADER;

    @Value("${server.api.authorization.token}")
    private String AUTHORIZATION_TOKEN;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        ApiAuthorizationFilter filter = new ApiAuthorizationFilter(AUTHORIZATION_HEADER, AUTHORIZATION_TOKEN);

        http
                .requiresChannel()
                .anyRequest()
                .requiresSecure();
        http
                .antMatcher("/api/**")
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(filter)
                .authorizeRequests()
                .antMatchers(HttpMethod.GET).permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED));
    }
}
