package com.www.backend.domain.asset.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AssetRawDto {
    private String type;

    private String url;

    @QueryProjection
    public AssetRawDto(String type, String url) {
        this.type = type;
        this.url = url;
    }
}
