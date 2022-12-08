package com.www.backend.domain.artist;

import com.www.backend.common.response.PaginationResponse;
import com.www.backend.common.response.SuccessResponse;
import com.www.backend.domain.artist.dto.CreateArtistParameter;
import com.www.backend.domain.artist.dto.SearchArtistRequest;
import com.www.backend.domain.artist.dto.UpdateArtistParameter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/artists")
public class ArtistController {
    private final ArtistService artistService;

    @Value("${spring.profiles.active:Unknown}")
    private static String profile;

    private final Environment env;

    @PostMapping
    public ResponseEntity<SuccessResponse> register(@Valid @RequestBody CreateArtistParameter createArtistParameter) {
        return ResponseEntity.ok(artistService.createArtist(createArtistParameter));
    }

    @GetMapping
    public ResponseEntity<SuccessResponse> getArtists() {
        System.out.println(env.getProperty("spring.profiles.active"));
        System.out.println(profile);
        return ResponseEntity.ok(artistService.getArtists());
    }

    @GetMapping("/detail")
    public ResponseEntity<SuccessResponse> getArtistByCode(@RequestHeader String code) {
        return ResponseEntity.ok(artistService.getArtistByCode(code));
    }

    @GetMapping("/pagination")
    public ResponseEntity<PaginationResponse> getArtistsByPagination(@RequestParam(defaultValue = "0", required = false) int page,
                                                                     @RequestParam(defaultValue = "10", required = false) int take) {
        SearchArtistRequest request = new SearchArtistRequest(page, take);

        return ResponseEntity.ok(artistService.getArtistsByPagination(request));
    }

    @GetMapping("/{artistId}")
    public ResponseEntity<SuccessResponse> getArtistDetail(@PathVariable Long artistId) {
        return ResponseEntity.ok(artistService.getArtistDetail(artistId));
    }

    @PutMapping()
    public ResponseEntity<SuccessResponse> updateArtist(@Valid @RequestBody UpdateArtistParameter parameter) {
        return ResponseEntity.ok(artistService.updateArtist(parameter));
    }

    @DeleteMapping("/{artistId}")
    public ResponseEntity<Void> deleteArtist(@PathVariable long artistId) {
        artistService.deleteArtist(artistId);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
}
