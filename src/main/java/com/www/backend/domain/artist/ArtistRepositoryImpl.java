package com.www.backend.domain.artist;

import com.www.backend.common.repository.BaseRepositoryImpl;
import com.www.backend.domain.artist.dto.ArtistDetailDto;
import com.www.backend.domain.artist.dto.ArtistDto;
import com.www.backend.domain.artist.dto.QArtistDetailDto;
import com.www.backend.domain.artist.dto.QArtistDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static com.www.backend.domain.artist.QArtist.artist;

public class ArtistRepositoryImpl extends BaseRepositoryImpl<Artist, Long> implements ArtistRepository {

    public ArtistRepositoryImpl(EntityManager em) {
        super(Artist.class, em);
    }

    @Override
    public Page<ArtistDto> searchArtists(Pageable pageable) {
        List<ArtistDto> artists = query.select(new QArtistDto(
                        artist.id,
                        artist.genre,
                        artist.nickname,
                        artist.profileImage
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
    public Optional<ArtistDetailDto> findById(long assetId) {
        return Optional.ofNullable(
               query
                    .select(new QArtistDetailDto(
                            artist.id,
                            artist.code,
                            artist.type,
                            artist.genre,
                            artist.name,
                            artist.nickname,
                            artist.email,
                            artist.contact,
                            artist.description,
                            artist.bio,
                            artist.profileImage,
                            artist.linkTree
                    ))
                    .from(artist)
                    .where(artist.id.eq(assetId), artist.deletedAt.isNull())
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

    @Override
    public Optional<Artist> findByCode(String code) {
        return Optional.ofNullable(
                query.selectFrom(artist)
                        .where(artist.code.eq(code),
                                artist.deletedAt.isNull())
                        .fetchOne()
        );
    }

    @Override
    public boolean existsByEmail(String type, String email) {
        Integer count = query
                .selectOne()
                .from(artist)
                .where(artist.type.eq(type), artist.email.eq(email))
                .fetchFirst();

        return count != null;
    }
}
