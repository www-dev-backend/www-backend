package com.www.backend.domain.genre.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.querydsl.core.annotations.QueryProjection;
import com.www.backend.common.Enum.EGenre;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GenreDto {
    private Long id;

    private EGenre genre;

    @QueryProjection
    public GenreDto(Long id, EGenre genre) {
        this.id = id;
        this.genre = genre;
    }
}
