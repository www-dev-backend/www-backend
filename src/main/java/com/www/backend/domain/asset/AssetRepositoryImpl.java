package com.www.backend.domain.asset;

import com.www.backend.common.repository.BaseRepositoryImpl;
import com.www.backend.domain.asset.dto.AssetDto;
import com.www.backend.domain.asset.dto.AssetRawDto;
import com.www.backend.domain.asset.dto.QAssetDto;
import com.www.backend.domain.asset.dto.QAssetRawDto;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.www.backend.domain.asset.QAsset.asset;

public class AssetRepositoryImpl extends BaseRepositoryImpl<Asset, Long> implements AssetRepository {
    public AssetRepositoryImpl(EntityManager em, DataSource dataSource) {
        super(Asset.class, em, dataSource);
    }

    @Override
    public Optional<List<AssetRawDto>> findAllByArtistId(long artistId) {
        return Optional.ofNullable(
                query.select(new QAssetRawDto(
                                asset.genre,
                                asset.type,
                                asset.url,
                                asset.isMain
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
                                asset.url,
                                asset.isMain
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
    public Optional<List<AssetDto>> findMainAssets() {
        return Optional.ofNullable(
                query.select(new QAssetDto(
                                asset.genre,
                                asset.type,
                                asset.url,
                                asset.isMain,
                                asset.artist.id
                        ))
                        .from(asset)
                        .where(asset.isMain.eq(true))
                        .orderBy(asset.artist.id.asc())
                        .fetch()
        );
    }

    @Override
    public Optional<List<AssetDto>> findMainAssetsByGenre(String genre) {
        return Optional.ofNullable(
                query.select(new QAssetDto(
                                asset.genre,
                                asset.type,
                                asset.url,
                                asset.isMain,
                                asset.artist.id
                        ))
                        .from(asset)
                        .where(asset.genre.eq(genre), asset.isMain.eq(true))
                        .orderBy(asset.artist.id.asc())
                        .fetch()
        );
    }

    @Override
    public void batchInsertAssets(List<Asset> assets) {
        String sql = "insert into assets " +
                "(genre, type, url, is_main, artist_id, created_at, updated_at) values(?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                Asset item = assets.get(i);
                ps.setString(1, item.getGenre());
                ps.setString(2, item.getType());
                ps.setString(3, item.getUrl());
                ps.setBoolean(4, item.getIsMain());
                ps.setLong(5, item.getArtist().getId());
                ps.setObject(6, LocalDateTime.now());
                ps.setObject(7, LocalDateTime.now());
            }

            @Override
            public int getBatchSize() {
                return assets.size();
            }
        });
    }

    @Override
    public Optional<List<Asset>> findAssetsByArtistId(long artistId) {
        return Optional.ofNullable(
                query.selectFrom(asset)
                        .where(asset.artist.id.eq(artistId), asset.deletedAt.isNull())
                        .orderBy(asset.createdAt.desc())
                        .fetch()
        );
    }
}
