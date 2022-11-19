package com.www.backend.domain.genre.dto;

import com.www.backend.common.Enum.EGenre;
import lombok.Getter;

@Getter
public class GetGenreParameter {
    private EGenre genre;

    public GetGenreParameter(EGenre genre) {
        this.genre = genre;
    }
}
