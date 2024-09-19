package com.email.spring_app.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.email.spring_app.dto.HttpResponseDTO;

public abstract class HttpResponseUtil {
    
    public static ResponseEntity<HttpResponseDTO> ok(final Object data) {
        HttpResponseDTO httpResponseDTO = new HttpResponseDTO();
        httpResponseDTO.setData(data);
        httpResponseDTO.setStatus(HttpStatus.OK);
        return new ResponseEntity<HttpResponseDTO>(httpResponseDTO, httpResponseDTO.getStatus());
    }

    public static ResponseEntity<HttpResponseDTO> unauthorize(final Object data) {
        HttpResponseDTO httpResponseDTO = new HttpResponseDTO();
        httpResponseDTO.setData(data);
        httpResponseDTO.setStatus(HttpStatus.UNAUTHORIZED);
        return new ResponseEntity<HttpResponseDTO>(httpResponseDTO, httpResponseDTO.getStatus());
    }
}
