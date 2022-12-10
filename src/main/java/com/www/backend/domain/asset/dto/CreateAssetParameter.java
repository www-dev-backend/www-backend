package com.www.backend.domain.asset.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter @Setter
public class CreateAssetParameter {
    @NotBlank
    private String genre;

    @NotBlank
    private String type;

    @NotBlank
    private String url;

    private Boolean isMain;
}
