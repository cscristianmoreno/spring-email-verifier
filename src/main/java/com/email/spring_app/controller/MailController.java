package com.email.spring_app.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.email.spring_app.dto.EmailDTO;
import com.email.spring_app.entity.EmailToken;
import com.email.spring_app.entity.Users;
import com.email.spring_app.models.controller.IMailController;
import com.email.spring_app.services.EmailTokenRepositoryService;
import com.email.spring_app.services.MailService;
import com.email.spring_app.services.UserRepositoryService;
import com.email.spring_app.utils.GenerateSecureCodeUtil;
import com.email.spring_app.utils.TemplateUtil;

import jakarta.mail.MessagingException;

@Controller
@ResponseBody
public class MailController implements IMailController {

    @Autowired
    private EmailTokenRepositoryService emailTokenRepositoryService;

    @Autowired
    private UserRepositoryService userRepositoryService;

    @Autowired
    private MailService mailService;

    @Value("${mail.uri.redirect}")
    private String redirect;

    /**
     * The function verifies an email token and returns a corresponding template based on its validity.
     * 
     * @param token The `token` parameter is a unique code that is used for verification purposes. It
     * is typically sent to a user's email or phone number as part of a verification process to confirm
     * their identity or access to a service. In the provided code snippet, the `verify` method takes
     * this token as input
     * @return A `ModelAndView` object is being returned from the `verify` method.
     */
    @Override
    public ModelAndView verify(String token) {
        Optional<EmailToken> emailToken = emailTokenRepositoryService.findByToken(token);
        boolean isValid = GenerateSecureCodeUtil.verifyCode(emailToken);
        emailTokenRepositoryService.setTokenConsumed(emailToken.get());
        
        String templateName;
        
        if (isValid) {
            templateName = "verified";
        }
        else {
            templateName = "expired";
        }
        
        return TemplateUtil.generate(templateName);
    }

    /**
     * The `newCode` function takes an `EmailDTO` object, retrieves a user from the repository based on
     * the email in the DTO, and sends an email to the user if found.
     * 
     * @param emailDTO The `emailDTO` parameter is an object of type `EmailDTO` which contains
     * information related to an email, such as the email address, subject, body, attachments, etc. In
     * the provided code snippet, the `newCode` method takes an `EmailDTO` object as a parameter and
     */
    @Override
    public void newCode(EmailDTO emailDTO) {
        System.out.println(emailDTO);
        userRepositoryService.findByEmail(emailDTO.getEmail()).ifPresent((user) -> {
            Users res = userRepositoryService.updateNewToken(user);

            try {
                mailService.send(res);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        });
    }
}
