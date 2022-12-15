package com.www.backend.domain.artwork.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter @Setter
public class SingleArtworkParameter {
    @NotBlank
    private String title;

    @NotNull
    private String description;
}
