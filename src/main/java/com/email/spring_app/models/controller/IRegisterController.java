package com.email.spring_app.models.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.email.spring_app.dto.HttpResponseDTO;
import com.email.spring_app.entity.Users;

import jakarta.mail.MessagingException;

public interface IRegisterController {

    @PostMapping("/register")
    ResponseEntity<HttpResponseDTO> register(@RequestBody Users users) throws MessagingException;
}
