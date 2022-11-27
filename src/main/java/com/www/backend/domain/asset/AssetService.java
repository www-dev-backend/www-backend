package com.www.backend.domain.asset;

import com.www.backend.common.response.SuccessResponse;
import com.www.backend.domain.artist.Artist;
import com.www.backend.domain.artist.ArtistRepository;
import com.www.backend.domain.asset.dto.AssetDto;
import com.www.backend.domain.asset.dto.AssetRawDto;
import com.www.backend.domain.asset.dto.CreateAssetParameter;
import com.www.backend.domain.asset.dto.UpdateAssetParameter;
import com.www.backend.domain.asset.mapper.AssetMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AssetService {
    private final ArtistRepository artistRepository;
    private final AssetRepository assetRepository;
    private final AssetMapper assetMapper;

    @Transactional
    public SuccessResponse createAsset(String code, CreateAssetParameter parameter) {
        Artist artist = artistRepository.findByCode(code)
                .orElseThrow(() -> new IllegalArgumentException("일치하는 이메일이 없습니다."));
        Asset asset = assetRepository.save(assetMapper.toEntity(parameter));

        artist.add(asset);

        return new SuccessResponse(assetMapper.toDto(asset));
    }

    @Transactional
    public SuccessResponse updateAsset(long assetId, UpdateAssetParameter parameter){
        Asset asset = assetRepository.findById(assetId)
                .orElseThrow(() -> new IllegalArgumentException("ID와 일치하는 에셋이 없습니다."));

        assetMapper.updateToEntity(parameter, asset);
        Asset updatedAsset = assetRepository.save(asset);
        return new SuccessResponse(updatedAsset);
    }

    public SuccessResponse getAssetsWithArtist() {
        List<Asset> assets = assetRepository.findAll();

        return new SuccessResponse(assets);
    }

    public SuccessResponse getAssets(long artistId) {
        List<AssetRawDto> assets = assetRepository.findAllByArtistId(artistId)
                .orElseThrow(() -> new IllegalArgumentException("일치하는 데이터가 없습니다."));

        return new SuccessResponse(assets);
    }

    public SuccessResponse getAssetsByArtistId(long artistId) {
        List<AssetRawDto> assets = assetRepository.findAllByArtistId(artistId)
                .orElseThrow(() -> new IllegalArgumentException("일치하는 데이터가 없습니다."));

        return new SuccessResponse(assets);
    }

    public SuccessResponse getAssetDetail(long assetId){
        Asset asset = assetRepository.findById(assetId)
                .orElseThrow(() -> new IllegalArgumentException("ID와 일치하는 에셋이 없습니다."));

        return new SuccessResponse(asset);
    }

    @Transactional
    public void deleteAsset(long assetId){
        Asset asset = assetRepository.findById(assetId)
                .orElseThrow(() -> new IllegalArgumentException("ID와 일치하는 에셋이 없습니다."));

        assetRepository.delete(asset);
    }
}
