package com.los.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class UnauthorizedException extends RuntimeException {

    private final String code;
    private final String message;

    public UnauthorizedException() {
        super(HttpStatus.UNAUTHORIZED.getReasonPhrase());
        this.code = String.valueOf(HttpStatus.UNAUTHORIZED.value());
        this.message = HttpStatus.UNAUTHORIZED.getReasonPhrase();
    }

    public UnauthorizedException(String message) {
        super(message);
        this.code = String.valueOf(HttpStatus.UNAUTHORIZED.value());
        this.message = message;
    }
}
