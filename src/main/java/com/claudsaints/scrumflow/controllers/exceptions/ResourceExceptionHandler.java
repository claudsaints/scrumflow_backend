package com.claudsaints.scrumflow.controllers.exceptions;

import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class ResourceExceptionHandler {
    @ExceptionHandler(ObjectNotFound.class)
    public ResponseEntity<Object> applicationException(ObjectNotFound e, WebRequest request){
        var resp = StandardError.builder()
                .error(e.getMessage())
                .timestamp( new Date())
                .path(request.getDescription(false))
                .status(HttpStatus.BAD_REQUEST.value())
                .build();

        return new ResponseEntity<>(resp,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(TokenInvalid.class)
    public ResponseEntity<Object> jwtException(TokenInvalid e, WebRequest request){
        var resp = StandardError.builder()
                .error(e.getMessage())
                .path(request.getDescription(false))
                .timestamp(new Date())
                .status(HttpStatus.NON_AUTHORITATIVE_INFORMATION.value())
                .build();
        return new ResponseEntity<>(resp, HttpStatus.NON_AUTHORITATIVE_INFORMATION);

    }

}
