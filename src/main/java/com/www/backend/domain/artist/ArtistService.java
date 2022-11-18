package com.www.backend.domain.artist;

import com.www.backend.common.dto.PaginationMeta;
import com.www.backend.common.response.PaginationResponse;
import com.www.backend.common.response.SuccessResponse;
import com.www.backend.common.util.PageableUtil;
import com.www.backend.domain.artist.dto.ArtistDto;
import com.www.backend.domain.artist.dto.CreateArtistParameter;
import com.www.backend.domain.artist.dto.SearchArtistRequest;
import com.www.backend.domain.artist.dto.UpdateArtistParameter;
import com.www.backend.domain.artist.mapper.ArtistMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PutMapping;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ArtistService {
    private final ArtistRepository artistRepository;
    private final ArtistMapper artistMapper;

    @Transactional
    public SuccessResponse createArtist(CreateArtistParameter createArtistParameter) {
        // TODO: email 중복 조건 검사

        Artist artist = artistRepository.save(artistMapper.toEntity(createArtistParameter));

        return new SuccessResponse(artistMapper.toDto(artist));
    }

    @Transactional
    public SuccessResponse updateArtist(long artistId, UpdateArtistParameter updateArtistParameter) {
        Artist artist = artistRepository.findById(artistId)
                .orElseThrow(() -> new IllegalArgumentException("ID와 일치하는 아티스트가 없습니다."));

        artistMapper.updateToEntity(updateArtistParameter, artist);
        Artist updatedArtist = artistRepository.save(artist);
        return new SuccessResponse(updatedArtist);
    }

    public PaginationResponse getArtists(SearchArtistRequest searchArtistRequest) {
        Pageable pageable = PageableUtil.of(searchArtistRequest.getPage(), searchArtistRequest.getTake());

        Page<ArtistDto> artists = artistRepository.searchArtists(pageable);

        return new PaginationResponse(artists.getContent(), PaginationMeta.of(artists));
    }

    public SuccessResponse getArtistDetail(Long artistId) {
        Artist artist = artistRepository.findById(artistId)
                .orElseThrow(() -> new IllegalArgumentException("ID와 일치하는 아티스트가 없습니다."));

        return new SuccessResponse(artistMapper.toDto(artist));
    }
}
