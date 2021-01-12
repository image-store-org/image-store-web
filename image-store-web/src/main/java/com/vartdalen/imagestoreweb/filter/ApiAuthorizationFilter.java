package com.vartdalen.imagestoreweb.filter;

import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

import javax.servlet.http.HttpServletRequest;

public class ApiAuthorizationFilter extends AbstractPreAuthenticatedProcessingFilter {

    private String authorizationHeader;

    public ApiAuthorizationFilter(String authorizationHeader) {
        this.authorizationHeader = authorizationHeader;
    }

    @Override
    protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
        return request.getHeader(authorizationHeader);
    }

    protected Object getPreAuthenticatedCredentials(HttpServletRequest request) { return null; }

}
