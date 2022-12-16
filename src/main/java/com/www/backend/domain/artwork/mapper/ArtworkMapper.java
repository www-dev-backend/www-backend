package com.www.backend.domain.artwork.mapper;

import com.www.backend.domain.artwork.Artwork;
import com.www.backend.domain.artwork.dto.*;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ArtworkMapper {
    ArtworkDto toDto(Artwork artwork);

    Artwork toEntity(CreateArtworkParameter createArtworkParameter);

    Artwork toEntity(SingleArtworkParameter parameter);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    void updateToEntity(ArtworkDto parameter, @MappingTarget Artwork artwork);
}
