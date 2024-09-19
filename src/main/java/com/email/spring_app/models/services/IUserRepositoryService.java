package com.email.spring_app.models.services;

import java.util.Optional;

import com.email.spring_app.entity.Users;
import com.email.spring_app.models.ICrudRepository;

public interface IUserRepositoryService extends ICrudRepository<Users> {
    Optional<Users> findByUsername(String username);

    Optional<Users> findByEmail(String email);

    Optional<Users> emailVerified(String email);

    Users updateNewToken(Users users);
}
