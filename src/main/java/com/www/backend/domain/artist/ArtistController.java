package com.www.backend.domain.artist;

import com.www.backend.common.response.SuccessResponse;
import com.www.backend.domain.artist.dto.CreateArtistParameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/artists")
public class ArtistController {
    private final ArtistService artistService;

    @PostMapping
    public ResponseEntity<SuccessResponse> register(@RequestBody CreateArtistParameter createArtistParameter) {
        return ResponseEntity.ok(artistService.createArtist(createArtistParameter));
    }
}
