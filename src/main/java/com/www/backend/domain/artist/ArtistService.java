package com.www.backend.domain.artist;

import com.www.backend.common.dto.PaginationMeta;
import com.www.backend.common.response.PaginationResponse;
import com.www.backend.common.response.SuccessResponse;
import com.www.backend.common.util.PageableUtil;
import com.www.backend.domain.artist.dto.*;
import com.www.backend.domain.artist.mapper.ArtistMapper;
import com.www.backend.domain.asset.AssetRepository;
import com.www.backend.domain.asset.dto.AssetRawDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ArtistService {
    private final ArtistRepository artistRepository;
    private final ArtistMapper artistMapper;
    private final AssetRepository assetRepository;

    @Transactional
    public SuccessResponse createArtist(CreateArtistParameter createArtistParameter) {
        // TODO: email 중복 조건 검사

        Artist artist = artistRepository.save(artistMapper.toEntity(createArtistParameter));
        artist.createCode();

        return new SuccessResponse(artistMapper.toDto(artist));
    }

    @Transactional
    public SuccessResponse updateArtist(long artistId, UpdateArtistParameter updateArtistParameter) {
        ArtistDto artistDto = artistRepository.findById(artistId)
                .orElseThrow(() -> new IllegalArgumentException("ID와 일치하는 아티스트가 없습니다."));

        Artist artist = artistMapper.toEntity(artistDto);
        artistMapper.updateToEntity(updateArtistParameter, artist);
        Artist updatedArtist = artistRepository.save(artist);
        return new SuccessResponse(updatedArtist);
    }

    public PaginationResponse getArtistsByPagination(SearchArtistRequest searchArtistRequest) {
        Pageable pageable = PageableUtil.of(searchArtistRequest.getPage(), searchArtistRequest.getTake());

        Page<ArtistDto> artists = artistRepository.searchArtists(pageable);

        return new PaginationResponse(artists.getContent(), PaginationMeta.of(artists));
    }

    public SuccessResponse getArtists() {
        List<Artist> all = artistRepository.findAll();

        List<ArtistDto> artists = all.stream()
                .map(artistMapper::toDto)
                .collect(Collectors.toList());

        return new SuccessResponse(artists);
    }

    public SuccessResponse getArtistDetail(long artistId) {
        ArtistDto artist = artistRepository.findById(artistId)
                .orElseThrow(() -> new IllegalArgumentException("ID와 일치하는 아티스트가 없습니다."));

        List<AssetRawDto> assets = assetRepository.findAllByArtistId(artistId)
                .orElseThrow(() -> new IllegalArgumentException("ID와 일치하는 아티스트가 없습니다."));

        return new SuccessResponse(new ArtistWrapperDto(artist, assets));
    }

    public SuccessResponse getArtistByCode(String code) {
        Artist artist = artistRepository.findByCode(code)
                .orElseThrow(() -> new IllegalArgumentException("Code와 일치하는 아티스트가 없습니다."));

        return new SuccessResponse(artistMapper.toDto(artist));
    }

    @Transactional
    public void deleteArtist(long artistId) {
        artistRepository.findById(artistId)
                .orElseThrow(() -> new IllegalArgumentException("ID와 일치하는 아티스트가 없습니다."));

        artistRepository.deleteById(artistId);
    }
}
