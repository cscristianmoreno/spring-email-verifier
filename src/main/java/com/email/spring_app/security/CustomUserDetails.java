package com.email.spring_app.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import com.email.spring_app.entity.Users;
import com.email.spring_app.models.security.ICustomUserDetails;

public class CustomUserDetails implements ICustomUserDetails {

    private final Users users;
    
    public CustomUserDetails(final Users users) {
        this.users = users;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthorityUtils.createAuthorityList("ROLE_USER");
    }

    @Override
    public String getPassword() {
        return users.getPassword();
    }

    @Override
    public String getUsername() {
        return users.getUsername();
    }

    @Override
    public String getEmail() {
        return users.getEmail();
    }

    @Override
    public boolean getEmailVerified() {
        return users.getEmailToken().isConsumed();
    }
    
}
