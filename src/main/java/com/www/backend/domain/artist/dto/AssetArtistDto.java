package com.www.backend.domain.artist.dto;

import lombok.Getter;

@Getter
public class AssetArtistDto {
    private long id;

    public AssetArtistDto(long id) {
        this.id = id;
    }
}
