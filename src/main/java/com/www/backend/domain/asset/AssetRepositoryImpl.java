package com.www.backend.domain.asset;

import com.www.backend.common.repository.BaseRepositoryImpl;
import com.www.backend.domain.asset.dto.AssetDto;
import com.www.backend.domain.asset.dto.QAssetDto;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static com.www.backend.domain.artist.QArtist.artist;
import static com.www.backend.domain.asset.QAsset.asset;

public class AssetRepositoryImpl extends BaseRepositoryImpl<Asset, Long> implements AssetRepository {
    public AssetRepositoryImpl(EntityManager em) {
        super(Asset.class, em);
    }

    @Override
    public Optional<List<AssetDto>> findAllByArtistId(long artistId) {
        return Optional.ofNullable(
                query.select(new QAssetDto(
                        asset.type,
                        asset.url,
                        asset.artist.genre,
                        asset.artist.name,
                        asset.artist.description
                ))
                .from(asset)
                .innerJoin(asset.artist, artist)
                .where(asset.deletedAt.isNull())
                .orderBy(asset.createdAt.desc())
                .fetch()
        );
    }
}
