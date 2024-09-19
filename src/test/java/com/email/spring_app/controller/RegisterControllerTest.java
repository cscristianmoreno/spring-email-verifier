package com.email.spring_app.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.email.spring_app.annotations.SpringBootTestInitializer;
import com.email.spring_app.entity.Users;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTestInitializer
@AutoConfigureMockMvc
public class RegisterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private Users users;

    @BeforeEach
    void setup() {
        users = new Users();
        users.setUsername("user");
        users.setPassword("password");
        users.setEmail("email@gmail.com");
    }

    @Test
    void testRegister() throws Exception {
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/register");
        builder.contentType(MediaType.APPLICATION_JSON);
        builder.content(objectMapper.writeValueAsString(users));

        ResultActions actions = mockMvc.perform(builder);
        MvcResult result = actions.andReturn();
        MockHttpServletResponse response = result.getResponse();
        
        assertNotNull(response);
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }
}
