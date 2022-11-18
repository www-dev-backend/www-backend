package com.www.backend.domain.artist;

import com.www.backend.common.repository.BaseRepository;
import com.www.backend.domain.artist.dto.ArtistDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ArtistRepository extends BaseRepository<Artist, Long> {
    Page<ArtistDto> searchArtists(Pageable pageable);
}
