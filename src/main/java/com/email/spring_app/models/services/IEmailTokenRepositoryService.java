package com.email.spring_app.models.services;

import java.util.Optional;

import com.email.spring_app.entity.EmailToken;
import com.email.spring_app.entity.Users;
import com.email.spring_app.models.ICrudRepository;

public interface IEmailTokenRepositoryService extends ICrudRepository<EmailToken> {
    Optional<EmailToken> findByToken(String token);

    EmailToken setTokenConsumed(EmailToken emailToken);
}
