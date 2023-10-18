package com.los.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ResourceNotFoundException extends RuntimeException {

    private final String code;
    private final String message;

    public ResourceNotFoundException() {
        super(HttpStatus.NOT_FOUND.getReasonPhrase());
        this.code = String.valueOf(HttpStatus.NOT_FOUND.value());
        this.message = HttpStatus.NOT_FOUND.getReasonPhrase();
    }

    public ResourceNotFoundException(String message) {
        super(message);
        this.code = String.valueOf(HttpStatus.NOT_FOUND.value());
        this.message = message;
    }
}
