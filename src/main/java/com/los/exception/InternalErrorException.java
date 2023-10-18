package com.los.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class InternalErrorException extends RuntimeException {

    private final String code;
    private final String message;

    public InternalErrorException() {
        super(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        this.code = String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value());
        this.message = HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();
    }

    public InternalErrorException(String message) {
        super(message);
        this.code = String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value());
        this.message = message;
    }
}
