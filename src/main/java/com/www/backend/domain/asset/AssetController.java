package com.www.backend.domain.asset;

import com.www.backend.common.response.SuccessResponse;
import com.www.backend.domain.asset.dto.CreateAssetWrapper;
import com.www.backend.domain.asset.dto.UpdateAssetParameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/asset")
public class AssetController {
    private final AssetService assetService;

    @PostMapping
    public ResponseEntity<SuccessResponse> create(@Valid @RequestBody CreateAssetWrapper wrapper) {
        return ResponseEntity.ok(assetService.createAsset(wrapper.getEmail(), wrapper.getAsset()));
    }

    @GetMapping
    public ResponseEntity<SuccessResponse> getAssetsByArtistId(@RequestParam long artistId) {
        return ResponseEntity.ok(assetService.getAssetsByArtistId(artistId));
    }

    @GetMapping("/all")
    public ResponseEntity<SuccessResponse> getAssets() {
        return ResponseEntity.ok(assetService.getAssets());
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