package com.www.backend.domain.artist.dto;

import com.www.backend.domain.asset.dto.AssetDto;
import com.www.backend.domain.asset.dto.AssetRawDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class ArtistWrapperDto {
    private ArtistDto artist;
    private List<AssetRawDto> assets;

    public ArtistWrapperDto(ArtistDto artist, List<AssetRawDto> assets) {
        this.artist = artist;
        this.assets = assets;
    }
}
