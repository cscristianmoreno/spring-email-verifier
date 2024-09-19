package com.email.spring_app.utils;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Base64;

import org.junit.jupiter.api.Test;

import com.email.spring_app.entity.Users;

public class GenerateSecureCodeUtilTest {

    @Test
    void testCode() throws NoSuchAlgorithmException {
        SecureRandom secureRandom = new SecureRandom();

        byte[] randomBytes = new byte[56];
        secureRandom.nextBytes(randomBytes);
        String encoded = Base64.getUrlEncoder().withoutPadding().encodeToString(randomBytes);
        System.out.println(encoded);
    }

    @Test
    void testExpireInHour() {
        LocalDateTime expire = LocalDateTime.now().plusSeconds(5);
        
        boolean compare = LocalDateTime.now().isBefore(expire);

        assertTrue(compare);
    }
}
