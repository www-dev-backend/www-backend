package com.www.backend.domain.artwork;

import com.www.backend.common.response.SuccessResponse;
import com.www.backend.domain.artist.Artist;
import com.www.backend.domain.artist.ArtistRepository;
import com.www.backend.domain.artist.dto.ArtistDto;
import com.www.backend.domain.artwork.dto.CreateArtworkParameter;
import com.www.backend.domain.artwork.dto.UpdateArtworkParameter;
import com.www.backend.domain.artwork.mapper.ArtworkMapper;
import com.www.backend.domain.asset.AssetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ArtworkService {
    private final ArtworkRepository artworkRepository;
    private final ArtworkMapper artworkMapper;
    private final AssetRepository assetRepository;
    private final ArtistRepository artistRepository;

    @Transactional
    public SuccessResponse createArtwork(String code, CreateArtworkParameter parameter){
        Artist artist = artistRepository.findByCode(code)
                .orElseThrow(() -> new IllegalArgumentException("Code와 일치하는 아티스트가 없습니다."));

        Artwork artwork = artworkRepository.save(artworkMapper.toEntity(parameter));
        artwork.registerArtist(artist);

        return new SuccessResponse(artworkMapper.toDto(artwork));
    }

    @Transactional
    public SuccessResponse updateArtwork(long artworkId, UpdateArtworkParameter parameter){
        Artwork artwork = artworkRepository.findById(artworkId)
                .orElseThrow(() -> new IllegalArgumentException("ID와 일치하는 아트워크가 없습니다."));

        artworkMapper.updateToEntity(parameter, artwork);
        Artwork updatedArtwork = artworkRepository.save(artwork);
        return new SuccessResponse(updatedArtwork);
    }

    public SuccessResponse getArtworkDetail(long artistId) {
        ArtistDto artist = artistRepository.findById(artistId)
                .orElseThrow(() -> new IllegalArgumentException("ID와 일치하는 아티스트가 없습니다."));

        assetRepository.findAllByArtistId(artistId)
                .orElseThrow(() -> new IllegalArgumentException("ID와 일치하는 아트워크가 없습니다."));

        Artwork artwork = artworkRepository.findById(artistId)
                .orElseThrow(() -> new IllegalArgumentException("ID와 일치하는 아트워크가 없습니다."));

        return new SuccessResponse(artwork);
    }

    @Transactional
    public void deleteArtwork(long artworkId) {
        Artwork artwork = artworkRepository.findById(artworkId)
                .orElseThrow(() -> new IllegalArgumentException("ID와 일치하는 아트워크가 없습니다."));

        artworkRepository.delete(artwork);
    }
}
