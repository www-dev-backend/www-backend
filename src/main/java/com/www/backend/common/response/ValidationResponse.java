package com.www.backend.common.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class ValidationResponse extends ErrorResponse {
    private final Map<String, String> errors;

    public ValidationResponse(Integer status, String path, String message, Map<String, String> errors) {
        super(status, path, message);
        this.errors = errors;
    }
}
