package com.email.spring_app.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.*;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.email.spring_app.annotations.SpringBootTestInitializer;
import com.email.spring_app.dto.EmailDTO;
import com.email.spring_app.entity.EmailToken;
import com.email.spring_app.entity.Users;
import com.email.spring_app.repository.EmailTokenRepository;
import com.email.spring_app.repository.UserRepository;
import com.email.spring_app.utils.GenerateSecureCodeUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;

@SpringBootTestInitializer
@AutoConfigureMockMvc
public class MailControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmailTokenRepository emailTokenRepository;

    @MockBean
    private UserRepository userRepository;

    private EmailToken emailToken;
    private Users user;
    private String token = GenerateSecureCodeUtil.generate();

    @Autowired
    private ObjectMapper objectMapper;


    @BeforeEach
    void setup() {
        user = new Users();
        user.setId(1);
        user.setUsername("user");
        user.setPassword("password");
        user.setEmail("example@gmail.com");

        LocalDateTime expire = GenerateSecureCodeUtil.expireInHour();

        emailToken = new EmailToken();
        emailToken.setToken(token);
        emailToken.setExpire(expire);

        user.setEmailToken(emailToken);
    }
    
    @Test
    void testVerify() throws Exception {
        given(emailTokenRepository.findByToken(token)).willReturn(Optional.of(emailToken));
        MockHttpServletResponse response = sendRequest();

        assertNotNull(response);
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    void testVerifyNotFound() throws Exception {
        given(emailTokenRepository.findByToken(token)).willReturn(Optional.empty());
        
        assertThrows(ServletException.class, () -> {
            sendRequest();
        });
    }

    private MockHttpServletResponse sendRequest() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/verify");
        requestBuilder.param("token", token);

        ResultActions resultActions = mockMvc.perform(requestBuilder);
        MvcResult result = resultActions.andReturn();
        MockHttpServletResponse response = result.getResponse();
        return response;
    }

    @Test
    void testNewCode() throws Exception {

        // GIVEN
        given(userRepository.findByEmail(user.getEmail())).willReturn(Optional.of(user));
        given(userRepository.save(user)).willReturn(user);

        EmailDTO emailDTO = new EmailDTO();
        emailDTO.setEmail(user.getEmail());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/new-code");
        requestBuilder.contentType(MediaType.APPLICATION_JSON);
        requestBuilder.content(objectMapper.writeValueAsString(emailDTO));

        ResultActions resultActions = mockMvc.perform(requestBuilder);
        MvcResult result = resultActions.andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertNotNull(response);
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }
}
