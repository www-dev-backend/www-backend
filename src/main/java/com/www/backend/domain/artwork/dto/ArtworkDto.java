package com.www.backend.domain.artwork.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.querydsl.core.annotations.QueryProjection;
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

    @QueryProjection
    public ArtworkDto(String title, String description) {
        this.title = title;
        this.description = description;
    }
}
