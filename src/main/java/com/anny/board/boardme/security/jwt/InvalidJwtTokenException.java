package com.anny.board.boardme.security.jwt;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED, reason = "invalid token")
public class InvalidJwtTokenException extends RuntimeException {
    public InvalidJwtTokenException() {
    }

    public InvalidJwtTokenException(String message) {
        super(message);
    }
}
