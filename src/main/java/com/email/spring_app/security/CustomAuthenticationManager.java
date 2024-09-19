package com.email.spring_app.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationManager implements AuthenticationManager {

    @Autowired
    private CustomAuthenticationProvider customAuthenticationProvider;

    /**
     * The `authenticate` method overrides the authentication process, using a custom authentication
     * provider and updating the security context with the authenticated information.
     * 
     * @param authentication The `authentication` parameter in the `authenticate` method represents the
     * authentication request made by a user trying to access a secured resource. It typically contains
     * the user's credentials, such as username and password, which need to be verified for
     * authentication. The method then delegates the authentication process to a custom authentication
     * provider
     * @return The `authenticate` method is returning the `Authentication` object from the
     * `SecurityContext`.
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Authentication authenticate = customAuthenticationProvider.authenticate(authentication);
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authenticate);
        return securityContext.getAuthentication();
    }
    
}
