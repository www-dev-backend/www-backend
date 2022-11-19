package com.www.backend.domain.genre.mapper;

import com.www.backend.domain.genre.Genre;
import com.www.backend.domain.genre.dto.CreateGenreParameter;
import com.www.backend.domain.genre.dto.GenreDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface GenreMapper {
    GenreDto toDto(Genre genre);

    Genre toEntity(CreateGenreParameter createGenreParameter);

    void updateToEntity(CreateGenreParameter createGenreParameter, @MappingTarget Genre genre);
}
