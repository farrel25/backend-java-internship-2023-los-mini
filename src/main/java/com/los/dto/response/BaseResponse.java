package com.los.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseResponse<T> {

    private String code;
    private String message;
    private T data;
    private Map<String, String> errors;
}
