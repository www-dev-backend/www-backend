package com.www.backend.domain.artist.dto;

import lombok.Getter;

@Getter
public class AssetArtistDto {
    private String genre;

    private String name;

    private String description;

    public AssetArtistDto(String genre, String name, String description) {
        this.genre = genre;
        this.name = name;
        this.description = description;
    }
}
