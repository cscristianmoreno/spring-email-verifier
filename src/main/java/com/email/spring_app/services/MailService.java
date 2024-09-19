package com.email.spring_app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.email.spring_app.entity.Users;
import com.email.spring_app.models.services.IMailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class MailService implements IMailService {

    @Value("${mail.uri.redirect}")
    private String redirectUri;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private TemplateEngine templateEngine;
    
    /**
     * This Java function sends an email to a user for email verification, generating a secure token and
     * saving it in a database.
     * 
     * @param users The `users` parameter in the `send` method represents the user for whom the email is
     * being sent. It contains information about the user, such as their email address. This method is
     * responsible for sending an email to the user for email verification purposes.
     */
    @Override
    public void send(final Users users) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);

        mimeMessageHelper.setFrom("verify@account.com");
        mimeMessageHelper.setSubject("Verifica tu correo electrónico");
        mimeMessageHelper.setTo(users.getEmail());

        String token = users.getEmailToken().getToken();
        
        String template = getTemplateProccess(users, token);
        mimeMessageHelper.setText(template, true);

        System.out.println(token);

        javaMailSender.send(mimeMessage);
    }
    
    /**
     * The function `getTemplateProccess` generates an email template using user information, a token,
     * and a template engine.
     * 
     * @param users The `users` parameter is likely an object representing user information, such as
     * their name, email, and other details. It is being passed to the `getTemplateProccess` method to
     * be used in generating an email template.
     * @param token A unique token used for authentication or authorization purposes.
     * @return The method `getTemplateProccess` is returning the processed email template using the
     * template engine.
     */
    private String getTemplateProccess(final Users users, final String token) {
        Context context = new Context();
        context.setVariable("user", users);
        context.setVariable("redirectUri", redirectUri);
        context.setVariable("token", token);
        return templateEngine.process("email", context);
    }
}
