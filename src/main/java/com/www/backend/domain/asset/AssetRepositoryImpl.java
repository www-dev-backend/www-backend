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
                        asset.genre,
                        asset.type,
                        asset.url
                ))
                .from(asset)
                .where(asset.artist.id.eq(artistId), asset.deletedAt.isNull())
                .orderBy(asset.createdAt.desc())
                .fetch()
        );
    }

    @Override
    public Optional<List<AssetRawDto>> findAssetsByGenre(String genre) {
        return Optional.ofNullable(
                query.select(new QAssetRawDto(
                            asset.genre,
                            asset.type,
                            asset.url
                    ))
                    .from(asset)
                    .where(asset.genre.eq(genre), asset.deletedAt.isNull())
                    .fetch()
        );
    }

    @Override
    public Boolean countByIsMain(long artistId) {
        Long count = query.select(
                        asset.count()
                )
                .from(asset)
                .where(asset.artist.id.eq(artistId))
                .fetchFirst();

        return count > 1;
    }

    @Override
    public Optional<List<AssetRawDto>> findMainAssets() {
        return Optional.ofNullable(
                query.select(new QAssetRawDto(
                                asset.genre,
                                asset.type,
                                asset.url
                        ))
                        .from(asset)
                        .where(asset.isMain.eq(true))
                        .fetch()
        );
    }

    @Override
    public Optional<List<AssetRawDto>> findMainAssetsByGenre(String genre) {
        return Optional.ofNullable(
                query.select(new QAssetRawDto(
                                asset.genre,
                                asset.type,
                                asset.url
                        ))
                        .from(asset)
                        .where(asset.genre.eq(genre), asset.isMain.eq(true))
                        .fetch()
        );
    }
}
