package com.email.spring_app.security;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.email.spring_app.annotations.SpringBootTestInitializer;
import com.email.spring_app.entity.Users;
import com.email.spring_app.repository.UserRepository;

@SpringBootTestInitializer
public class CustomAuthenticationProviderTest {

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private CustomAuthenticationProvider customAuthenticationProvider;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private Users users;
    private UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken;

    @BeforeEach 
    void setup() {
        users = new Users();
        users.setUsername("user");
        users.setPassword(passwordEncoder.encode("user"));
        users.setEmail("cristianmorenoweb@gmail.com");

        usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken("user", "user");
    }

    @Test
    void testAuthenticate() {
        given(userRepository.findByEmail(users.getUsername())).willReturn(Optional.of(users));

        Authentication authentication = customAuthenticationProvider.authenticate(usernamePasswordAuthenticationToken);

        System.out.println(authentication);
        assertNotNull(authentication);
    }
}
