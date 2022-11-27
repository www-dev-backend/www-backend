package com.www.backend.domain.asset.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter @Setter
public class UpdateAssetParameter {
    @NotBlank
    private String type;

    @NotBlank
    private String url;
}
