package com.www.backend.domain.artist.dto;

import com.www.backend.domain.asset.dto.AssetDto;
import com.www.backend.domain.asset.dto.AssetRawDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class ArtistWrapperDto {
    private Long id;

    private String genre;

    private String name;

    private String email;

    private String instagramAccount;

    private String description;

    private String bio;
    private List<AssetRawDto> assets;

    public ArtistWrapperDto(ArtistDto artist, List<AssetRawDto> assets) {
        this.id = artist.getId();
        this.genre = artist.getGenre();
        this.name = artist.getName();
        this.email = artist.getEmail();
        this.instagramAccount = artist.getInstagramAccount();
        this.description = artist.getDescription();
        this.bio = artist.getBio();
        this.assets = assets;
    }
}
