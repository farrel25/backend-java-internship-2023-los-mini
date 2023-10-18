package com.los.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BadRequestException extends RuntimeException {

    private final String code;
    private final String message;

    public BadRequestException() {
        super(HttpStatus.BAD_REQUEST.getReasonPhrase());
        this.code = String.valueOf(HttpStatus.BAD_REQUEST.value());
        this.message = HttpStatus.BAD_REQUEST.getReasonPhrase();
    }

    public BadRequestException(String message) {
        super(message);
        this.code = String.valueOf(HttpStatus.BAD_REQUEST.value());
        this.message = message;
    }
}
