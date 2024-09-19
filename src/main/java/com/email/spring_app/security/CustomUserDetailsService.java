package com.email.spring_app.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.email.spring_app.entity.Users;
import com.email.spring_app.services.UserRepositoryService;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepositoryService userRepositoryService;

   /**
    * This function loads user details by username and returns a custom user details object.
    * 
    * @param username The `username` parameter in the `loadUserByUsername` method represents the email
    * address of the user for whom the user details are being loaded. The method retrieves the user
    * details based on this email address from the database and returns a `CustomUserDetails` object
    * that contains the user information.
    * @return The `loadUserByUsername` method is returning an instance of `CustomUserDetails` which is
    * a custom implementation of Spring Security's `UserDetails` interface. This custom implementation
    * likely contains additional information about the user retrieved from the database, such as roles,
    * permissions, and other user details.
    */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Users> result = userRepositoryService.findByEmail(username);

        if (result.isEmpty()) {
            throw new UsernameNotFoundException("email not found");
        }

        Users user = result.get();

        return new CustomUserDetails(user);
    }
}
