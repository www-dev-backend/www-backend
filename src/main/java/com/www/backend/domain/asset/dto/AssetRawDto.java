package com.www.backend.domain.asset.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AssetRawDto {
    private String genre;
    private String type;
    private String url;

    private Boolean isMain;

    @QueryProjection
    public AssetRawDto(String genre, String type, String url, Boolean isMain) {
        this.genre = genre;
        this.type = type;
        this.url = url;
        this.isMain = isMain;
    }
}
