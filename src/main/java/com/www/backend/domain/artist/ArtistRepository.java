package com.www.backend.domain.artist;

import com.www.backend.common.repository.BaseRepository;
import com.www.backend.domain.artist.dto.ArtistDetailDto;
import com.www.backend.domain.artist.dto.ArtistDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;


public interface ArtistRepository extends BaseRepository<Artist, Long> {
    Page<ArtistDto> searchArtists(Pageable pageable);

    Optional<ArtistDetailDto> findById(long assetId);

    Optional<Artist> findByEmail(String email);

    Optional<Artist> findByCode(String code);

    boolean existsByEmail(String email);
}
