package com.www.backend.domain.artwork.mapper;

import com.www.backend.domain.artwork.Artwork;
import com.www.backend.domain.artwork.dto.ArtworkDto;
import com.www.backend.domain.artwork.dto.CreateArtworkParameter;
import com.www.backend.domain.artwork.dto.UpdateArtworkParameter;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ArtworkMapper {
    ArtworkDto toDto(Artwork artwork);

    Artwork toEntity(CreateArtworkParameter createArtworkParameter);

    void updateToEntity(UpdateArtworkParameter updateArtworkParameter, @MappingTarget Artwork artwork);
}
