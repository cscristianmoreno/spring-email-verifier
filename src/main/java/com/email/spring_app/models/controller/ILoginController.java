package com.email.spring_app.models.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.email.spring_app.dto.HttpResponseDTO;
import com.email.spring_app.dto.LoginDTO;

public interface ILoginController {
    /**
     * The login function takes a LoginDTO object as input and returns a ResponseEntity containing an
     * HttpResponseDTO object.
     * 
     * @param loginDTO The `loginDTO` parameter is a data transfer object (DTO) that contains the
     * necessary information for a user to log in, such as username and password. It is typically sent
     * as a request body in a POST request to the login endpoint of an API.
     * @return ResponseEntity<HttpResponseDTO>
     */
    @PostMapping("/login")
    ResponseEntity<HttpResponseDTO> login(@RequestBody LoginDTO loginDTO);  
}
