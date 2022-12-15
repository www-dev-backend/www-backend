package com.www.backend.domain.asset.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.querydsl.core.annotations.QueryProjection;
import com.www.backend.domain.artist.dto.AssetArtistDto;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AssetDto {
    private String type;

    private String url;

    private Boolean isMain;

    private long artistId;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private AssetArtistDto artist;

    @QueryProjection
    public AssetDto(String type, String url, Boolean isMain, long artistId) {
        this.type = type;
        this.url = url;
        this.isMain = isMain;
        this.artist = new AssetArtistDto(artistId);
    }
}
