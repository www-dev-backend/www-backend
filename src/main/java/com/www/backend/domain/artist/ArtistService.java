package com.www.backend.domain.artist;

import com.www.backend.common.dto.PaginationMeta;
import com.www.backend.common.exceptions.EntityNotFoundException;
import com.www.backend.common.response.PaginationResponse;
import com.www.backend.common.response.SuccessResponse;
import com.www.backend.common.util.PageableUtils;
import com.www.backend.domain.artist.dto.*;
import com.www.backend.domain.artist.exceptions.DuplicateEmailException;
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
        boolean isExists = artistRepository.existsByEmail(createArtistParameter.getType(), createArtistParameter.getEmail());

        if (isExists) {
            throw new DuplicateEmailException("이미 등록된 이메일이 있습니다.");
        }

        Artist artist = artistRepository.save(artistMapper.toEntity(createArtistParameter));
        artist.createCode();

        return new SuccessResponse(artistMapper.toDetailDto(artist));
    }

    @Transactional
    public SuccessResponse updateArtist(Long artistId, UpdateArtistParameter updateArtistParameter) {
        Artist artist = artistRepository.findById(artistId)
                .orElseThrow(() -> new EntityNotFoundException("요청한 ID와 일치하는 아티스트가 없습니다."));

        artistMapper.updateToEntity(updateArtistParameter, artist);
        return new SuccessResponse(artistMapper.toDetailDto(artist));
    }

    public PaginationResponse getArtistsByPagination(SearchArtistRequest searchArtistRequest) {
        Pageable pageable = PageableUtils.of(searchArtistRequest.getPage(), searchArtistRequest.getTake());

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
        ArtistDetailDto artist = artistRepository.findById(artistId)
                .orElseThrow(() -> new EntityNotFoundException("요청한 ID와 일치하는 아티스트가 없습니다."));

        List<AssetRawDto> assets = assetRepository.findAllByArtistId(artistId)
                .orElseThrow(() -> new EntityNotFoundException());

        return new SuccessResponse(new ArtistWrapperDto(artist, assets));
    }

    public SuccessResponse getArtistByCode(String code) {
        Artist artist = artistRepository.findByCode(code)
                .orElseThrow(() -> new EntityNotFoundException());

        List<AssetRawDto> assets = assetRepository.findAllByArtistId(artist.getId())
                .orElseThrow(() -> new EntityNotFoundException());

        return new SuccessResponse(new ArtistWrapperDto(artistMapper.toDetailDto(artist), assets));
    }

    @Transactional
    public void deleteArtist(long artistId) {
        artistRepository.findById(artistId)
                .orElseThrow(() -> new EntityNotFoundException());

        artistRepository.deleteById(artistId);
    }
}
