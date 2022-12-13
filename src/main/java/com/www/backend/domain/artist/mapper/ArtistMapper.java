package com.www.backend.domain.artist.mapper;

import com.www.backend.domain.artist.Artist;
import com.www.backend.domain.artist.dto.ArtistDetailDto;
import com.www.backend.domain.artist.dto.ArtistDto;
import com.www.backend.domain.artist.dto.CreateArtistParameter;
import com.www.backend.domain.artist.dto.UpdateArtistParameter;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ArtistMapper {
    ArtistDto toDto(Artist artist);

    ArtistDetailDto toDetailDto(Artist artist);

    Artist toEntity(CreateArtistParameter createArtistParameter);

    Artist toEntity(ArtistDto artistDto);

    void updateToEntity(UpdateArtistParameter updateArtistParameter, @MappingTarget Artist artist);
}
