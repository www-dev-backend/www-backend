package com.www.backend.domain.artwork;

import com.www.backend.common.response.SuccessResponse;
import com.www.backend.domain.artwork.dto.CreateArtworkParameter;
import com.www.backend.domain.artwork.dto.UpdateArtworkParameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/artworks")
public class ArtworkController {
    private final ArtworkService artworkService;

    @PostMapping
    public ResponseEntity<SuccessResponse> registerArtwork(@RequestHeader String code,
                                                           @Valid @RequestBody CreateArtworkParameter parameter) {
        return ResponseEntity.ok(artworkService.createArtwork(code, parameter));
    }

    @GetMapping
    public ResponseEntity<SuccessResponse> getArtworkByCode(@RequestHeader String code) {
        return ResponseEntity.ok(artworkService.getArtworkDetailByCode(code));
    }

    @GetMapping("/{artworkId}")
    public ResponseEntity<SuccessResponse> getArtworkDetail(@PathVariable long artworkId) {
        return ResponseEntity.ok(artworkService.getArtworkDetail(artworkId));
    }

    @PutMapping()
    public ResponseEntity<SuccessResponse> updateArtwork(@RequestHeader String code,
                                                         @Valid @RequestBody UpdateArtworkParameter parameter) {
        return ResponseEntity.ok(artworkService.updateArtwork(code, parameter));
    }

    @DeleteMapping()
    public ResponseEntity<Void> deleteArtwork(@RequestHeader String code) {
        artworkService.deleteArtwork(code);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

//    @GetMapping
//    public ResponseEntity<PaginationResponse> getArtworks(@RequestParam(defaultValue = "0", required = false) int page,
//                                                         @RequestParam(defaultValue = "10", required = false) int take) {
//        SearchArtworkRequest request = new SearchArtworkRequest(page, take);
//
////        return ResponseEntity.ok(artworkService.getArtworks(request));
//    }

}
