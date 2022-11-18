package com.www.backend.common.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
public class SuccessResponse {
    private Object data;

    public SuccessResponse(Object data) {
        this.data = data;
    }
}
