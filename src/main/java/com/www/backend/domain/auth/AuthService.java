package com.www.backend.domain.auth;

import com.www.backend.common.exceptions.EntityNotFoundException;
import com.www.backend.common.response.SuccessResponse;
import com.www.backend.domain.artist.Artist;
import com.www.backend.domain.artist.ArtistRepository;
import com.www.backend.domain.artist.dto.ArtistDto;
import com.www.backend.domain.artist.mapper.ArtistMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService {
    private final ArtistRepository artistRepository;
    private final ArtistMapper artistMapper;

    public SuccessResponse login(String code) {
        Artist artist = artistRepository.findByCode(code)
                .orElseThrow(() -> new EntityNotFoundException("요청한 ID와 일치하는 아티스트가 없습니다."));

        return new SuccessResponse(artist);
    }
}
