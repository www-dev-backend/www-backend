package com.www.backend.domain.artwork.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter @Setter
public class AssetParameter {
    @NotBlank
    private String code;

//    @NotBlank
//    private String List<Asset>;
}
