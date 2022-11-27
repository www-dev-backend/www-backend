package com.www.backend.domain.asset;

import com.www.backend.common.repository.BaseRepositoryImpl;
import com.www.backend.domain.asset.dto.AssetRawDto;
import com.www.backend.domain.asset.dto.QAssetRawDto;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static com.www.backend.domain.asset.QAsset.asset;

public class AssetRepositoryImpl extends BaseRepositoryImpl<Asset, Long> implements AssetRepository {
    public AssetRepositoryImpl(EntityManager em) {
        super(Asset.class, em);
    }

    @Override
    public Optional<List<AssetRawDto>> findAllByArtistId(long artistId) {
        return Optional.ofNullable(
                query.select(new QAssetRawDto(
                        asset.type,
                        asset.url
                ))
                .from(asset)
                .where(asset.artist.id.eq(artistId), asset.deletedAt.isNull())
                .orderBy(asset.createdAt.desc())
                .fetch()
        );
    }
}
