package com.email.spring_app.models.services;

import com.email.spring_app.entity.Users;

import jakarta.mail.MessagingException;

public interface IMailService {
    void send(Users users) throws MessagingException;
}
