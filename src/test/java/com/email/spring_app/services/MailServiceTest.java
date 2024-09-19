package com.email.spring_app.services;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;

import com.email.spring_app.annotations.SpringBootTestInitializer;
import com.email.spring_app.entity.EmailToken;
import com.email.spring_app.entity.Users;
import com.email.spring_app.utils.GenerateSecureCodeUtil;

import jakarta.mail.MessagingException;

@SpringBootTestInitializer
public class MailServiceTest {

    @Autowired
    private MailService mailService;

    @Test
    void testSend() throws MessagingException {
        EmailToken emailToken = new EmailToken();
        emailToken.setToken(GenerateSecureCodeUtil.generate());
        emailToken.setExpire(GenerateSecureCodeUtil.expireInHour());

        Users users = new Users();
        users.setUsername("user");
        users.setPassword("user");
        users.setEmail("email@gmail.com");
        users.setEmailToken(emailToken);
        mailService.send(users);
    }
}
