package com.email.spring_app.dto;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class HttpResponseDTO {
    private Object data;
    private HttpStatus status;
}
