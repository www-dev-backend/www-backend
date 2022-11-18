package com.www.backend.common.response;

import com.www.backend.common.dto.PaginationMeta;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PaginationResponse extends SuccessResponse {
    private final PaginationMeta meta;

    public PaginationResponse(Object data, PaginationMeta paginationMeta) {
        super(data);
        this.meta = paginationMeta;
    }
}
