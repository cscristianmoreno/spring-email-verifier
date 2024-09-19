package com.email.spring_app.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.email.spring_app.annotations.SpringBootTestInitializer;
import com.email.spring_app.entity.EmailToken;
import com.email.spring_app.utils.GenerateSecureCodeUtil;


@SpringBootTestInitializer
public class EmailTokenRepositoryServiceTest {

    @Autowired
    private EmailTokenRepositoryService emailTokenRepositoryService;
    
    private EmailToken emailToken;

    private String token;

    @BeforeEach
    void setup() {
        token = GenerateSecureCodeUtil.generate();

        EmailToken newEmailToken = new EmailToken();
        newEmailToken.setToken(token);
        newEmailToken.setExpire(LocalDateTime.now().plusSeconds(60L));
        
        emailToken = emailTokenRepositoryService.save(newEmailToken);
    }

    @Test
    @Order(1)
    void testSave() {
        System.out.println(emailToken);
        assertNotNull(emailToken);
    }

    @Test
    @Order(2)
    void testUpdate() {
        System.out.println(emailToken);
        String newToken = GenerateSecureCodeUtil.generate();
        emailToken.setToken(newToken);
        emailToken.setExpire(GenerateSecureCodeUtil.expireInHour());
        EmailToken result = emailTokenRepositoryService.update(emailToken);
        System.out.println(result);

        assertNotNull(result);

    }

    @Test
    @Order(3)
    void testFindById() {
        Optional<EmailToken> result = emailTokenRepositoryService.findById(emailToken.getId());

        assertNotNull(result);
        assertEquals(emailToken.getId(), result.get().getId());
    }

    @Test
    @Order(4)
    void testFindByToken() {
        Optional<EmailToken> result = emailTokenRepositoryService.findByToken(token);

        System.out.println(result);
        assertNotNull(result);
    }

    @Test
    @Order(5)
    void testFindByTokenNotFound() {
        Optional<EmailToken> result = emailTokenRepositoryService.findByToken("");

        System.out.println(result);
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    @Order(5)
    void testDeleteById() {
        emailTokenRepositoryService.deleteById(emailToken.getId());
    }
}
