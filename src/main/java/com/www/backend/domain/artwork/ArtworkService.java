package com.www.backend.domain.artwork;

import com.www.backend.common.exceptions.EntityNotFoundException;
import com.www.backend.common.response.SuccessResponse;
import com.www.backend.domain.artist.Artist;
import com.www.backend.domain.artist.ArtistRepository;
import com.www.backend.domain.artist.mapper.ArtistMapper;
import com.www.backend.domain.artwork.dto.ArtworkDto;
import com.www.backend.domain.artwork.dto.ArtworkWrapperDto;
import com.www.backend.domain.artwork.dto.CreateArtworkParameter;
import com.www.backend.domain.artwork.dto.UpdateArtworkParameter;
import com.www.backend.domain.artwork.mapper.ArtworkMapper;
import com.www.backend.domain.asset.AssetRepository;
import com.www.backend.domain.asset.dto.AssetRawDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ArtworkService {
    private final ArtworkRepository artworkRepository;
    private final ArtworkMapper artworkMapper;
    private final AssetRepository assetRepository;
    private final ArtistRepository artistRepository;
    private final ArtistMapper artistMapper;

    @Transactional
    public SuccessResponse createArtwork(String code, CreateArtworkParameter parameter){
        Artist artist = artistRepository.findByCode(code)
                .orElseThrow(() -> new EntityNotFoundException("요청한 Code와 일치하는 아티스트가 없습니다."));

        Artwork artwork = artworkRepository.save(artworkMapper.toEntity(parameter));
        artwork.registerArtist(artist);

        return new SuccessResponse(artworkMapper.toDto(artwork));
    }

    @Transactional
    public SuccessResponse updateArtwork(String code, UpdateArtworkParameter parameter){
        Artist artist = artistRepository.findByCode(code)
                .orElseThrow(() -> new EntityNotFoundException("요청한 Code와 일치하는 아티스트가 없습니다."));

        Artwork artwork = artworkRepository.findByArtistId(artist.getId())
                .orElseThrow(() -> new EntityNotFoundException("요청한 ArtistID와 일치하는 아트워크가 없습니다."));

        artworkMapper.updateToEntity(parameter, artwork);
        return new SuccessResponse(artworkMapper.toDto(artwork));
    }

    public SuccessResponse getArtworkDetail(long artworkId) {
        Artwork artwork = artworkRepository.findById(artworkId)
                .orElseThrow(() -> new EntityNotFoundException("요청한 ArtistID와 일치하는 아트워크가 없습니다."));

        return new SuccessResponse(artwork);
    }

    @Transactional
    public SuccessResponse getArtworkDetailByCode(String code) {
        Artist artist = artistRepository.findByCode(code)
                .orElseThrow(() -> new EntityNotFoundException("요청한 Code와 일치하는 아티스트가 없습니다."));

        List<AssetRawDto> assets = assetRepository.findAllByArtistId(artist.getId())
                .orElseThrow(() -> new EntityNotFoundException("요청한 ArtistID와 일치하는 에셋이 없습니다."));

        Artwork artwork = artworkRepository.findByArtistId(artist.getId())
                .orElseThrow(() -> new EntityNotFoundException("요청한 자원이 없습니다."));

        return new SuccessResponse(new ArtworkWrapperDto(artworkMapper.toDto(artwork), artistMapper.toDto(artist), assets));
    }

    @Transactional
    public void deleteArtwork(String code) {
        Artist artist = artistRepository.findByCode(code)
                .orElseThrow(() -> new EntityNotFoundException("요청한 Code와 일치하는 아티스트가 없습니다."));

        Artwork artwork = artworkRepository.findByArtistId(artist.getId())
                .orElseThrow(() -> new EntityNotFoundException("요청한 ArtistID와 일치하는 아트워크가 없습니다."));

        artworkRepository.delete(artwork);
    }
}
