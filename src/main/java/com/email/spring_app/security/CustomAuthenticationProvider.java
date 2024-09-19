package com.email.spring_app.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.email.spring_app.exceptions.EmailNotVerifiedException;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * This Java function authenticates a user by checking their username, password, and email verification
     * status.
     * 
     * @param authentication The `authentication` parameter in the `authenticate` method represents the
     * user's authentication request. It typically contains the user's credentials, such as username and
     * password, that are provided during the login process.
     * @return The `authenticate` method is returning an `Authentication` object created using the
     * `UsernamePasswordAuthenticationToken` constructor, which includes the username, password, and
     * authorities obtained from the `CustomUserDetails` object.
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        CustomUserDetails userDetails = (CustomUserDetails) customUserDetailsService.loadUserByUsername(username);
        
        if (!userDetails.getEmailVerified()) {
            throw new EmailNotVerifiedException("Email not verified!");
        }

        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Bad credentials");
        }
        
        Authentication authenticate = new UsernamePasswordAuthenticationToken(
            username,
            password,
            userDetails.getAuthorities()
        );

        return authenticate;
    }

   /**
    * The function checks if the authentication class is assignable from
    * UsernamePasswordAuthenticationToken.
    * 
    * @param authentication The `authentication` parameter in the `supports` method is a Class object
    * representing the type of authentication being checked. In this case, the method checks if the
    * provided authentication class is assignable from `UsernamePasswordAuthenticationToken` class.
    * @return The method is returning a boolean value based on whether the provided authentication
    * class is assignable from the `UsernamePasswordAuthenticationToken` class.
    */
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.isAssignableFrom(UsernamePasswordAuthenticationToken.class);
    }
    
}
