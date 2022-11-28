package com.www.backend.domain.artwork.dto;

import com.www.backend.domain.artist.dto.ArtistDto;
import com.www.backend.domain.asset.dto.AssetRawDto;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter @Setter
public class ArtworkWrapperDto {
    @NotBlank
    private String title;

    @NotBlank
    private String description;

    private ArtistDto artist;

    private List<AssetRawDto> assets;

    public ArtworkWrapperDto(ArtworkDto artwork, ArtistDto artist, List<AssetRawDto> assets) {
        this.title = artwork.getTitle();
        this.description = artwork.getDescription();
        this.artist = artist;
        this.assets = assets;
    }
}
