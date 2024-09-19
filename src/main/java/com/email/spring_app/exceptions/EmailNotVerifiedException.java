package com.email.spring_app.exceptions;

public class EmailNotVerifiedException extends RuntimeException {
    
    public EmailNotVerifiedException(final String message) {
        super(message);
    }
}
