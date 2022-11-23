package com.www.backend.domain.asset;


import com.www.backend.common.repository.BaseRepository;
import com.www.backend.domain.artist.dto.ArtistDto;
import com.www.backend.domain.asset.dto.AssetDto;

import java.util.List;
import java.util.Optional;

public interface AssetRepository extends BaseRepository<Asset, Long> {
    Optional<List<AssetDto>> findAllByArtistId(long artistId);
}
