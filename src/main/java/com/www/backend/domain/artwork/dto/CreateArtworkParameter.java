package com.www.backend.domain.artwork.dto;

import com.www.backend.domain.asset.dto.CreateAssetParameter;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter @Setter
public class CreateArtworkParameter {
    @NotNull
    private String title;

    @NotNull
    private String description;

    @NotNull
    private List<CreateAssetParameter> assets;
}
