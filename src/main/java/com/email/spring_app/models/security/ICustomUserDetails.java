package com.email.spring_app.models.security;

import org.springframework.security.core.userdetails.UserDetails;

public interface ICustomUserDetails extends UserDetails {
    String getEmail();

    boolean getEmailVerified();
}
