package com.los.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ForbiddenException extends RuntimeException {

    private final String code;
    private final String message;

    public ForbiddenException() {
        super(HttpStatus.FORBIDDEN.getReasonPhrase());
        this.code = String.valueOf(HttpStatus.FORBIDDEN.value());
        this.message = HttpStatus.FORBIDDEN.getReasonPhrase();
    }

    public ForbiddenException(String message) {
        super(message);
        this.code = String.valueOf(HttpStatus.FORBIDDEN.value());
        this.message = message;
    }
}
