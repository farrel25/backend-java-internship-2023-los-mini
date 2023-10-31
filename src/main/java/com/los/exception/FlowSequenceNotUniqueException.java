package com.los.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class FlowSequenceNotUniqueException extends RuntimeException{
    public FlowSequenceNotUniqueException(String message){
        super(message);
    }
}
