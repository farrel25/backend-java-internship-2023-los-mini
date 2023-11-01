package com.los.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;


@Getter
public class FlowSequenceNotUniqueException extends RuntimeException{
    private final String code;
    private final String message;

    public FlowSequenceNotUniqueException(){
        super(HttpStatus.CONFLICT.getReasonPhrase());
        this.code = String.valueOf(HttpStatus.CONFLICT.value());
        this.message = HttpStatus.CONFLICT.getReasonPhrase();
    }

    public FlowSequenceNotUniqueException(String message){
        super(message);
        this.code = String.valueOf(HttpStatus.CONFLICT.value());
        this.message = message;
    }
}
