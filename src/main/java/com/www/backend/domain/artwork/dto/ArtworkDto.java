package com.www.backend.domain.artwork.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter @Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ArtworkDto {
    @NotBlank
    private String title;

    @NotBlank
    private String description;
}
