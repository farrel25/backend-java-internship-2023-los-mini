package com.los.controller.base;

import com.los.dto.response.BaseResponse;
import com.los.exception.*;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

@RestControllerAdvice
@Slf4j
public class ExceptionHandlerController {

    @ExceptionHandler(value = {Exception.class, RuntimeException.class})
    public ResponseEntity<BaseResponse<?>> defaultExceptionHandler(Exception exception) {

        BaseResponse<?> baseResponse = new BaseResponse<>();
        baseResponse.setCode(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
        baseResponse.setMessage("System error. Contact our team");

        exception.printStackTrace();
        log.error("ERROR MESSAGE: {}", exception.getMessage());

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(baseResponse);
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity<BaseResponse<?>> constraintViolationExceptionHandler(ConstraintViolationException constraintViolationException) {

        BaseResponse<?> baseResponse = new BaseResponse<>();
        baseResponse.setCode(String.valueOf(HttpStatus.BAD_REQUEST.value()));
        baseResponse.setMessage("Oops, failed to process your data");
        baseResponse.setErrors(new HashMap<>());

        constraintViolationException.getConstraintViolations().forEach(constraintViolation -> {
            String[] pathNames = constraintViolation.getPropertyPath().toString().split("[.]");
            baseResponse.getErrors().put(
                    pathNames[pathNames.length - 1],
                    constraintViolation.getMessage()
            );
        });

        constraintViolationException.printStackTrace();
        log.error("ERROR MESSAGE: {}", constraintViolationException.getMessage());

        return new ResponseEntity<>(baseResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<BaseResponse<?>> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException methodArgumentNotValidException) {

        BaseResponse<?> baseResponse = new BaseResponse<>();
        baseResponse.setCode(String.valueOf(HttpStatus.BAD_REQUEST.value()));
        baseResponse.setMessage("Oops, failed to process your data");
        baseResponse.setErrors(new HashMap<>());

        methodArgumentNotValidException.getBindingResult()
                .getAllErrors()
                .forEach(objectError -> baseResponse.getErrors()
                        .put(
                                ((FieldError) objectError).getField(),
                                objectError.getDefaultMessage()
                        )
                );

        methodArgumentNotValidException.printStackTrace();
        log.error("ERROR MESSAGE: {}", methodArgumentNotValidException.getMessage());

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(baseResponse);
    }

    @ExceptionHandler(value = BadRequestException.class)
    public ResponseEntity<BaseResponse<?>> badRequestExceptionHandler(BadRequestException badRequestException) {

        BaseResponse<?> baseResponse = new BaseResponse<>();
        baseResponse.setCode(badRequestException.getCode());
        baseResponse.setMessage(badRequestException.getMessage());

        badRequestException.printStackTrace();
        log.error("ERROR MESSAGE: {}", badRequestException.getMessage());

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(baseResponse);
    }

    @ExceptionHandler(value = ForbiddenException.class)
    public ResponseEntity<BaseResponse<?>> forbiddenExceptionHandler(ForbiddenException forbiddenException) {

        BaseResponse<?> baseResponse = new BaseResponse<>();
        baseResponse.setCode(forbiddenException.getCode());
        baseResponse.setMessage(forbiddenException.getMessage());

        forbiddenException.printStackTrace();
        log.error("ERROR MESSAGE: {}", forbiddenException.getMessage());

        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(baseResponse);
    }

    @ExceptionHandler(value = InternalErrorException.class)
    public ResponseEntity<BaseResponse<?>> internalErrorExceptionHandler(InternalErrorException internalErrorException) {

        BaseResponse<?> baseResponse = new BaseResponse<>();
        baseResponse.setCode(internalErrorException.getCode());
        baseResponse.setMessage(internalErrorException.getMessage());

        internalErrorException.printStackTrace();
        log.error("ERROR MESSAGE: {}", internalErrorException.getMessage());

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(baseResponse);
    }

    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ResponseEntity<BaseResponse<?>> resourceNotFoundExceptionHandler(ResourceNotFoundException resourceNotFoundException) {

        BaseResponse<?> baseResponse = new BaseResponse<>();
        baseResponse.setCode(resourceNotFoundException.getCode());
        baseResponse.setMessage(resourceNotFoundException.getMessage());

        resourceNotFoundException.printStackTrace();
        log.error("ERROR MESSAGE: {}", resourceNotFoundException.getMessage());

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(baseResponse);
    }

    @ExceptionHandler(value = UnauthorizedException.class)
    public ResponseEntity<BaseResponse<?>> unauthorizedExceptionHandler(UnauthorizedException unauthorizedException) {

        BaseResponse<?> baseResponse = new BaseResponse<>();
        baseResponse.setCode(unauthorizedException.getMessage());
        baseResponse.setMessage(unauthorizedException.getMessage());

        unauthorizedException.printStackTrace();
        log.error("ERROR MESSAGE: {}", unauthorizedException.getMessage());

        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(baseResponse);
    }

    @ExceptionHandler(value = AuthenticationException.class)
    public ResponseEntity<BaseResponse<?>> authenticationExceptionHandler(AuthenticationException authenticationException) {

        BaseResponse<?> baseResponse = new BaseResponse<>();
        baseResponse.setCode(String.valueOf(HttpStatus.UNAUTHORIZED.value()));
        baseResponse.setMessage("Oops, invalid credential or unauthorized");

        authenticationException.printStackTrace();
        log.error("ERROR MESSAGE: {}", authenticationException.getMessage());

        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(baseResponse);
    }
}
