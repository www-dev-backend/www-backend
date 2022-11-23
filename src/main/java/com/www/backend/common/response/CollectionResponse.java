package com.www.backend.common.response;

import lombok.Getter;

@Getter
public class CollectionResponse {
    private Object[] data;
    private long count;


    public CollectionResponse(Object[] data, long count) {
        this.data = data;
        this.count = count;
    }
}
