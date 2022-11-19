package com.www.backend.domain.genre;

import com.www.backend.common.Enum.EGenre;
import com.www.backend.common.repository.BaseRepository;

public interface GenreRepository extends BaseRepository<Genre, Long> {
     Genre getGenreBy(EGenre genre);
}
