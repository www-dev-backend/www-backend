package com.www.backend.domain.asset;


import com.www.backend.common.repository.BaseRepository;
import com.www.backend.domain.asset.dto.AssetRawDto;

import java.util.List;
import java.util.Optional;

public interface AssetRepository extends BaseRepository<Asset, Long> {
    Optional<List<AssetRawDto>> findAllByArtistId(long artistId);

    Optional<List<AssetRawDto>> findAssetsByGenre(String genre);

    Boolean countByIsMain(long artistId);

    Optional<List<AssetRawDto>> findMainAssets();
    Optional<List<AssetRawDto>> findMainAssetsByGenre(String genre);
}
