package com.email.spring_app.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class EmailToken extends BaseEntity {
    private String token;
    private boolean consumed;

    private LocalDateTime expire;
}
