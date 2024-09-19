package com.email.spring_app.services;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.email.spring_app.entity.EmailToken;
import com.email.spring_app.entity.Users;
import com.email.spring_app.models.services.IUserRepositoryService;
import com.email.spring_app.repository.UserRepository;
import com.email.spring_app.utils.GenerateSecureCodeUtil;

/**
 * The UserRepositoryService class implements IUserRepositoryService interface and provides methods for
 * saving, updating, finding by ID, deleting, and finding by username or email in a user repository.
 */
@Service
public class UserRepositoryService implements IUserRepositoryService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Users save(Users entity) {
        String password = entity.getPassword();
        entity.setPassword(passwordEncoder.encode(password));
        String code = GenerateSecureCodeUtil.generate();
        LocalDateTime expire = GenerateSecureCodeUtil.expireInHour(); 

        EmailToken emailToken = new EmailToken();
        emailToken.setToken(code);
        emailToken.setExpire(expire);

        entity.setEmailToken(emailToken);
        return userRepository.save(entity);
    }

    @Override
    public Users update(Users entity) {
        return userRepository.save(entity);
    }

    @Override
    public Optional<Users> findById(int id) {
        return userRepository.findById(id);
    }

    @Override
    public void deleteById(int id) {
        userRepository.deleteById(id);
    }

    @Override
    public Optional<Users> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Optional<Users> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    
    @Override
    public Users updateNewToken(Users users) {
        String token = GenerateSecureCodeUtil.generate();
        users.getEmailToken().setToken(token);
        users.getEmailToken().setConsumed(false);
        return save(users);
    }

    @Override
    public Optional<Users> emailVerified(String email) {
        return null;
    }
}
