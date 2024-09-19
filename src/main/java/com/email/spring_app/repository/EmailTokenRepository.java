package com.email.spring_app.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.email.spring_app.entity.EmailToken;

@Repository
public interface EmailTokenRepository extends CrudRepository<EmailToken, Integer> {
    Optional<EmailToken> findByToken(String token); 
 
    @Query(value = "SELECT * FROM selectAfterUpdate(:id, :token, :consumed, :expire)", nativeQuery = true)
    EmailToken update(int id, String token, boolean consumed, LocalDateTime expire);
}
