package com.www.backend.domain.artist;

import com.www.backend.common.response.SuccessResponse;
import com.www.backend.domain.artist.dto.CreateArtistParameter;
import com.www.backend.domain.artist.mapper.ArtistMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ArtistService {
    private final ArtistRepository artistRepository;
    private final ArtistMapper artistMapper;

    @Transactional
    public SuccessResponse createArtist(CreateArtistParameter createArtistParameter) {
        Artist artist = artistRepository.save(artistMapper.toEntity(createArtistParameter));

        return new SuccessResponse(artistMapper.toDto(artist));
    }
}
