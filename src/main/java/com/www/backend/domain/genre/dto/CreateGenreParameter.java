package com.www.backend.domain.genre.dto;

import com.www.backend.common.Enum.EGenre;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class CreateGenreParameter {
    @NotEmpty()
    private EGenre genre;
}
