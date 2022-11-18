package com.www.backend.domain.artist;

import com.www.backend.common.response.PaginationResponse;
import com.www.backend.common.response.SuccessResponse;
import com.www.backend.domain.artist.dto.CreateArtistParameter;
import com.www.backend.domain.artist.dto.SearchArtistRequest;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/artists")
public class ArtistController {
    private final ArtistService artistService;

    @PostMapping
    public ResponseEntity<SuccessResponse> register(@Valid @RequestBody CreateArtistParameter createArtistParameter) {
        return ResponseEntity.ok(artistService.createArtist(createArtistParameter));
    }

    @GetMapping
    public ResponseEntity<PaginationResponse> getArtists(@RequestParam(defaultValue = "0", required = false) int page,
                                                         @RequestParam(defaultValue = "10", required = false) int take) {
        SearchArtistRequest request = new SearchArtistRequest(page, take);

        return ResponseEntity.ok(artistService.getArtists(request));
    }


    @GetMapping("/{artistId}")
    public ResponseEntity<SuccessResponse> getArtistDetail(@PathVariable Long artistId) {
        return ResponseEntity.ok(artistService.getArtistDetail(artistId));
    }
}
