package com.www.backend.domain.asset.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter @Setter
public class ArtworkAssetWrapper {
    @NotBlank
    private List<AssetRawDto> images;

    @NotBlank
    private List<AssetRawDto> videos;
}
