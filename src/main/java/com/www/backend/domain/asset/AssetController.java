package com.www.backend.domain.asset;

import com.www.backend.common.response.SuccessResponse;
import com.www.backend.domain.asset.dto.CreateAssetParameter;
import com.www.backend.domain.asset.dto.UpdateAssetParameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/asset")
@CrossOrigin(origins = "http://localhost:5173, http://localhost:3000, http://localhost:3001, https://wwweb.kr, https://m.wwweb.kr")
public class AssetController {
    private final AssetService assetService;

    @PostMapping("/register")
    public ResponseEntity<SuccessResponse> register(
            @RequestHeader String code,
            @Valid @RequestBody CreateAssetParameter parameter
    ) {
        return ResponseEntity.ok(assetService.registerAsset(code, parameter));
    }

    @PostMapping
    public ResponseEntity<SuccessResponse> create(
            @RequestHeader String code,
            @Valid @RequestBody CreateAssetParameter parameter) {
        return ResponseEntity.ok(assetService.createAsset(code, parameter));
    }

    @GetMapping
    public ResponseEntity<SuccessResponse> getAssetsByArtistId(@RequestHeader String code) {
        return ResponseEntity.ok(assetService.getAssetsByArtistCode(code));
    }

    @GetMapping("/{genre}")
    public ResponseEntity<SuccessResponse> getAssetsByGenre(@PathVariable String genre) {
        return ResponseEntity.ok(assetService.getMainAssetsByGenre(genre));
    }

    @GetMapping("/all")
    public ResponseEntity<SuccessResponse> getAssetsWithArtist() {
        return ResponseEntity.ok(assetService.getMainAssets());
    }

    @PutMapping("/{assetId}")
    public ResponseEntity<SuccessResponse> updateAsset(@PathVariable long assetId,
                                                       @Valid @RequestBody UpdateAssetParameter parameter){
        return ResponseEntity.ok(assetService.updateAsset(assetId, parameter));
    }

    @DeleteMapping("/{assetId}")
    public ResponseEntity<Void> deleteAsset(@PathVariable long assetId) {
        assetService.deleteAsset(assetId);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
}