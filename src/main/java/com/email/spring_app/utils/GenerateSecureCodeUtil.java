package com.email.spring_app.utils;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.Duration;

import java.util.Base64;
import java.util.Optional;
import com.email.spring_app.entity.EmailToken;
import com.email.spring_app.exceptions.EmailTokenNotFoundException;


public abstract class GenerateSecureCodeUtil {
    
    public static String generate() {
        SecureRandom secureRandom = new SecureRandom();

        byte[] randomBytes = new byte[32];
        secureRandom.nextBytes(randomBytes);
        String code = Base64.getUrlEncoder().withoutPadding().encodeToString(randomBytes);
        return code;
    }

    public static LocalDateTime expireInHour() {
        LocalDateTime localTime = LocalDateTime.now();
        long seconds = Duration.ofHours(1).getSeconds();
        return localTime.plusSeconds(seconds); 
    }

    public static boolean verifyCode(final Optional<EmailToken> emailToken) {
        if (emailToken.isEmpty()) {
            throw new EmailTokenNotFoundException("You don't have access!");
        }

        if (emailToken.get().isConsumed()) {
            throw new EmailTokenNotFoundException("Code already was been consumed!");
        }

        LocalDateTime expire = emailToken.get().getExpire();
        return isValid(expire);
    }

    public static boolean isValid(final LocalDateTime expire) {
        return LocalDateTime.now().isBefore(expire);
    }
}
