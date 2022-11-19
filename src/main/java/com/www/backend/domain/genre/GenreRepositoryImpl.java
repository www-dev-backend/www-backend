package com.www.backend.domain.genre;

import com.www.backend.common.Enum.EGenre;
import com.www.backend.common.repository.BaseRepositoryImpl;

import javax.persistence.EntityManager;

public class GenreRepositoryImpl extends BaseRepositoryImpl<Genre, Long> implements GenreRepository {

    public GenreRepositoryImpl(EntityManager em) {
        super(Genre.class, em);
    }

    @Override
    public Genre getGenreBy(EGenre genre) {
        System.out.println(genre);
        if (genre == null) {
            return null;
        }
    }
}