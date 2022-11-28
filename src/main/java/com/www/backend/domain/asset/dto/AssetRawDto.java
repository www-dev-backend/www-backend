package com.www.backend.domain.asset.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AssetRawDto {
    private String genre;
    private String type;

    private String url;

    @QueryProjection
    public AssetRawDto(String genre, String type, String url) {
        this.genre = genre;
        this.type = type;
        this.url = url;
    }
}
