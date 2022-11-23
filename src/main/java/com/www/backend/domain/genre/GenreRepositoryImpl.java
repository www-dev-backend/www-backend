package com.www.backend.domain.genre;

import com.www.backend.common.repository.BaseRepositoryImpl;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityManager;


@Slf4j
public class GenreRepositoryImpl extends BaseRepositoryImpl<Genre, Long> implements GenreRepository {
//    private final QGenre genre = QGenre.genre1;
    public GenreRepositoryImpl(EntityManager em) {
        super(Genre.class, em);
    }
}