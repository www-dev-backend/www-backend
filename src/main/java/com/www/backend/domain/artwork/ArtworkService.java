package com.www.backend.domain.artwork;

import com.www.backend.common.response.SuccessResponse;
import com.www.backend.domain.artwork.dto.CreateArtworkParameter;
import com.www.backend.domain.artwork.dto.UpdateArtworkParameter;
import com.www.backend.domain.artwork.mapper.ArtworkMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ArtworkService {
    private final ArtworkRepository artworkRepository;

    private final ArtworkMapper artworkMapper;

    @Transactional
    public SuccessResponse createArtwork(CreateArtworkParameter parameter){
        Artwork artwork = artworkRepository.save(artworkMapper.toEntity(parameter));

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

    public SuccessResponse getArtworkDetail(long artworkId) {
        Artwork artwork = artworkRepository.findById(artworkId)
                .orElseThrow(() -> new IllegalArgumentException("ID와 일치하는 아트워크가 없습니다."));
        return new SuccessResponse(artwork);
    }

//    public PaginationResponse getArtworks(SearchArtworkRequest request) {
//        Pageable pageable = PageableUtil.of(request.getPage(), request.getTake());
//
//        Page<ArtworkDto> artworks = artworkRepository.searchArtworks(pageable);
//
//        return new PaginationResponse(artworks.getContent(), PaginationMeta.of(artworks));
//    }

    @Transactional
    public void deleteArtwork(long artworkId) {
        Artwork artwork = artworkRepository.findById(artworkId)
                .orElseThrow(() -> new IllegalArgumentException("ID와 일치하는 아트워크가 없습니다."));

        artworkRepository.delete(artwork);
    }
}
