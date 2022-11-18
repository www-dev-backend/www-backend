package com.www.backend.common.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;

@Getter
public class PaginationMeta {
    private final int page;
    private final int take;
    private final long count;
    private final int totalPage;
    private final boolean hasPreviousPage;
    private final boolean hasNextPage;

    public PaginationMeta(int page, int take, long count, int totalPage, boolean hasPreviousPage, boolean hasNextPage) {
        this.page = page;
        this.take = take;
        this.count = count;
        this.totalPage = totalPage;
        this.hasPreviousPage = hasPreviousPage;
        this.hasNextPage = hasNextPage;
    }

    public static PaginationMeta of(Page<?> data) {
        return new PaginationMeta(data.getNumber(),
                data.getSize(),
                data.getTotalElements(),
                data.getTotalPages(),
                data.hasPrevious(),
                data.hasNext());
    }
}
