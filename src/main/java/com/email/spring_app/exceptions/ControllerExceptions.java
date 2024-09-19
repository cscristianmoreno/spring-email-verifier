package com.email.spring_app.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.email.spring_app.dto.HttpResponseDTO;
import com.email.spring_app.utils.HttpResponseUtil;

@ControllerAdvice
public class ControllerExceptions {
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<HttpResponseDTO> controllerException(final Exception exception) {
        return HttpResponseUtil.unauthorize(exception.getMessage());
    }
}
