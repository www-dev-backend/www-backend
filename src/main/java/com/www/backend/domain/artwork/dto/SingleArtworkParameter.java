package com.www.backend.domain.artwork.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter @Setter
public class SingleArtworkParameter {
    @NotBlank
    private String title;

    @NotBlank
    private String description;
}
