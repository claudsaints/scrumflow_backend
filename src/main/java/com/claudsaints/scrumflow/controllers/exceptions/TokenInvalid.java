package com.claudsaints.scrumflow.controllers.exceptions;

import com.auth0.jwt.exceptions.JWTVerificationException;

public class TokenInvalid extends JWTVerificationException {
    public TokenInvalid(String message) {
        super(message);
    }
}
