package com.www.backend.domain.artwork;

import com.www.backend.common.repository.BaseRepository;
import com.www.backend.domain.artwork.dto.ArtworkDto;

import java.util.Optional;

public interface ArtworkRepository extends BaseRepository<Artwork, Long> {
    Optional<Artwork> findByArtistId(long artistId);
}
