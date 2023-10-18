package com.los.controller.base;

import com.los.dto.response.BaseResponse;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@RestControllerAdvice
public class CustomResponseAdvice  implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(@NonNull MethodParameter returnType, @NonNull Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(
            Object body,
            @NonNull MethodParameter returnType,
            @NonNull MediaType selectedContentType,
            @NonNull Class<? extends HttpMessageConverter<?>> selectedConverterType,
            @NonNull ServerHttpRequest request,
            @NonNull ServerHttpResponse response
    ) {
        if (returnType.getContainingClass().isAnnotationPresent(RestController.class)) {
            if (body instanceof BaseResponse || body instanceof byte[]) {
                return body;

            } else {
                BaseResponse<Object> baseResponse = new BaseResponse<>();

                baseResponse.setCode(String.valueOf(HttpStatus.OK.value()));
                baseResponse.setMessage("Success");
                baseResponse.setData(body);

                if (returnType.hasMethodAnnotation(PostMapping.class)) {
                    response.setStatusCode(HttpStatus.CREATED);
                }

                System.out.println("asd");
                System.out.println("asd");
                return baseResponse;
            }
        }

        return body;
    }
}
