package com.email.spring_app.models.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.email.spring_app.dto.EmailDTO;

import jakarta.mail.MessagingException;

public interface IMailController {
    
    
    @GetMapping("/verify")
    ModelAndView verify(@RequestParam String token);

    @PostMapping("/new-code")
    void newCode(@RequestBody EmailDTO emailDTO) throws MessagingException;
}
