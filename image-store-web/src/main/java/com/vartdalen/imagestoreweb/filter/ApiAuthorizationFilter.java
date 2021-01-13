package com.vartdalen.imagestoreweb.filter;

import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

import javax.servlet.http.HttpServletRequest;

public class ApiAuthorizationFilter extends AbstractPreAuthenticatedProcessingFilter {

    private final String authorizationHeader;
    private final String authorizationToken;

    public ApiAuthorizationFilter(String authorizationHeader, String authorizationToken) {
        this.authorizationHeader = authorizationHeader;
        this.authorizationToken = authorizationToken;
        this.setAuthenticationManager(manager -> {
            String principal = (String) manager.getPrincipal();
            manager.setAuthenticated(this.authorizationToken.equals(principal));
            return manager;
        });
    }

    @Override
    protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
        return request.getHeader(authorizationHeader);
    }

    protected Object getPreAuthenticatedCredentials(HttpServletRequest request) { return null; }

}
