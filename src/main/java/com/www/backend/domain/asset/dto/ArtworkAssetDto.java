package com.www.backend.domain.asset.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ArtworkAssetDto {
    private String fileId;
    private String genre;
    private String type;
    private String url;

    @QueryProjection
    public ArtworkAssetDto(String fileId, String genre, String type, String url) {
        this.fileId = fileId;
        this.genre = genre;
        this.type = type;
        this.url = url;
    }
}
