package com.email.spring_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import com.email.spring_app.dto.HttpResponseDTO;
import com.email.spring_app.entity.Users;
import com.email.spring_app.models.controller.IRegisterController;
import com.email.spring_app.services.MailService;
import com.email.spring_app.services.UserRepositoryService;
import com.email.spring_app.utils.HttpResponseUtil;

import jakarta.mail.MessagingException;

@Controller
@ResponseBody
public class RegisterController implements IRegisterController {

    @Autowired
    private UserRepositoryService userRepositoryService;

    @Autowired
    private MailService mailService;

    /**
     * The `register` function saves a user to the repository, sends a confirmation email, and returns
     * an HTTP response with the saved user data.
     * 
     * @param users The `users` parameter in the `register` method is an object of the `Users` class.
     * It is used to represent user data that is being registered. This object likely contains
     * information such as the user's name, email, password, and any other relevant details needed for
     * registration.
     * @return The method `register` is returning a `ResponseEntity` object containing an
     * `HttpResponseDTO` object.
     */
    @Override
    public ResponseEntity<HttpResponseDTO> register(Users users) throws MessagingException {
        Users result = userRepositoryService.save(users);
        mailService.send(result);
        return HttpResponseUtil.ok(result);
    }
}
