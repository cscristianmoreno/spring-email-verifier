package com.email.spring_app.exceptions;

public class EmailTokenNotFoundException extends RuntimeException {
    
    public EmailTokenNotFoundException(String message) {
        super(message);
    }
}
