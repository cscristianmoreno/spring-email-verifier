package com.email.spring_app.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.email.spring_app.entity.EmailToken;
import com.email.spring_app.entity.Users;
import com.email.spring_app.models.services.IEmailTokenRepositoryService;
import com.email.spring_app.repository.EmailTokenRepository;

@Service
public class EmailTokenRepositoryService implements IEmailTokenRepositoryService {

    @Autowired
    private EmailTokenRepository emailTokenRepository;

    @Override
    public EmailToken save(EmailToken entity) {
        return emailTokenRepository.save(entity);
    }

    @Override
    public EmailToken update(EmailToken entity) {
        return emailTokenRepository.update(
            entity.getId(),
            entity.getToken(),
            entity.isConsumed(),
            entity.getExpire()
        );
    }

    @Override
    public Optional<EmailToken> findById(int id) {
        return emailTokenRepository.findById(id);
    }

    @Override
    public void deleteById(int id) {
        emailTokenRepository.deleteById(id);
    }

    @Override
    public Optional<EmailToken> findByToken(String token) {
        return emailTokenRepository.findByToken(token);
    }

    @Override
    public EmailToken setTokenConsumed(EmailToken emailToken) {
        emailToken.setConsumed(true);
        return emailTokenRepository.save(emailToken);
    }
}
