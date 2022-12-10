package com.www.backend.domain.artwork;

import com.www.backend.common.repository.BaseRepositoryImpl;
import com.www.backend.domain.artwork.dto.ArtworkDto;
import com.www.backend.domain.artwork.dto.QArtworkDto;

import javax.persistence.EntityManager;
import java.util.Optional;

import static com.www.backend.domain.artwork.QArtwork.artwork;

public class ArtworkRepositoryImpl extends BaseRepositoryImpl<Artwork, Long> implements ArtworkRepository {
    public ArtworkRepositoryImpl(EntityManager em) {
        super(Artwork.class, em);
    }

    @Override
    public Optional<Artwork> findByArtistId(long artistId) {
        return Optional.ofNullable(
                query.selectFrom(artwork)
                        .where(artwork.artist.id.eq(artistId),
                                artwork.deletedAt.isNull())
                        .fetchOne()
        );
    }

    @Override
    public Boolean checkExistsArtwork(long artistId) {
        Long count = query
                .select(artwork.count())
                .from(artwork)
                .where(artwork.artist.id.eq(artistId))
                .fetchFirst();

        return count == 1;
    }
}
