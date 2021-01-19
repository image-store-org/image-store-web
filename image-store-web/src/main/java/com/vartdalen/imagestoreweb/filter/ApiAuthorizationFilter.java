// package com.vartdalen.imagestoreweb.filter;

// import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

// import javax.servlet.http.HttpServletRequest;

// public class ApiAuthorizationFilter extends AbstractPreAuthenticatedProcessingFilter {

//     private final String AUTHORIZATION_HEADER;
//     private final String AUTHORIZATION_TOKEN;

//     public ApiAuthorizationFilter(String authorizationHeader, String authorizationToken) {
//         this.AUTHORIZATION_HEADER = authorizationHeader;
//         this.AUTHORIZATION_TOKEN = authorizationToken;
//         this.setAuthenticationManager(manager -> {
//             String principal = (String) manager.getPrincipal();
//             manager.setAuthenticated(this.AUTHORIZATION_TOKEN.equals(principal));
//             return manager;
//         });
//     }

//     @Override
//     protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
//         return request.getHeader(AUTHORIZATION_HEADER);
//     }

//     protected Object getPreAuthenticatedCredentials(HttpServletRequest request) { return null; }

// }
