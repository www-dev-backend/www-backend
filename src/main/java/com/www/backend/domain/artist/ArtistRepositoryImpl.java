package com.www.backend.domain.artist;

import com.www.backend.common.repository.BaseRepositoryImpl;
import com.www.backend.domain.artist.dto.ArtistDto;
import com.www.backend.domain.artist.dto.QArtistDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static com.www.backend.domain.artist.QArtist.artist;
import static com.www.backend.domain.asset.QAsset.asset;

public class ArtistRepositoryImpl extends BaseRepositoryImpl<Artist, Long> implements ArtistRepository {

    public ArtistRepositoryImpl(EntityManager em) {
        super(Artist.class, em);
//        this.support = new ArtistSupport(notice);
    }

    @Override
    public Page<ArtistDto> searchArtists(Pageable pageable) {
        List<ArtistDto> artists = query.select(new QArtistDto(
                        artist.id,
                        artist.genre,
                        artist.name,
                        artist.email,
                        artist.instagramAccount,
                        artist.description,
                        artist.bio,
//                        artist.assets,
                        artist.createdAt,
                        artist.updatedAt
                ))
                .from(artist)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(artist.createdAt.desc())
                .fetch();

        Long count = query.select(artist.count())
                .from(artist)
                .fetchOne();

        return new PageImpl<>(artists, pageable, count);
    }

    @Override
    public Optional<ArtistDto> findById(long assetId) {
        return Optional.ofNullable(
               query
                    .select(new QArtistDto(
                            artist.id,
                            artist.genre,
                            artist.name,
                            artist.email,
                            artist.instagramAccount,
                            artist.description,
                            artist.bio,
                            artist.createdAt,
                            artist.updatedAt
                    ))
                    .from(artist)
                    .where(artist.id.eq(assetId), artist.deletedAt.isNull())
                    .orderBy(artist.createdAt.desc())
                    .fetchOne()
        );
    }

    @Override
    public Optional<Artist> findByEmail(String email) {
        return Optional.ofNullable(
                query
                    .selectFrom(artist)
                    .where(artist.email.eq(email),
                            artist.deletedAt.isNull())
                    .fetchOne()
        );
    }
}
