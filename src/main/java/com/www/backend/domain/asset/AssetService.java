package com.www.backend.domain.asset;

import com.www.backend.common.exceptions.EntityNotFoundException;
import com.www.backend.common.response.SuccessResponse;
import com.www.backend.domain.artist.Artist;
import com.www.backend.domain.artist.ArtistRepository;
import com.www.backend.domain.asset.dto.AssetDto;
import com.www.backend.domain.asset.dto.AssetRawDto;
import com.www.backend.domain.asset.dto.CreateAssetParameter;
import com.www.backend.domain.asset.dto.UpdateAssetParameter;
import com.www.backend.domain.asset.exceptions.AssetPolicyException;
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
                .orElseThrow(() -> new EntityNotFoundException("요청한 Code와 일치하는 아티스트가 없습니다."));

        Boolean exists = assetRepository.countByIsMain(artist.getId());
        if (parameter.getIsMain() && exists) {
            throw new AssetPolicyException("대표 작품은 하나만 설정 가능합니다.");
        }

        Asset asset = assetRepository.save(assetMapper.toEntity(parameter));

        artist.add(asset);

        return new SuccessResponse(assetMapper.toRawDto(asset));
    }

    @Transactional
    public SuccessResponse updateAsset(long assetId, UpdateAssetParameter parameter){
        Asset asset = assetRepository.findById(assetId)
                .orElseThrow(() -> new EntityNotFoundException("요청한 ID와 일치하는 에셋이 없습니다."));

        assetMapper.updateToEntity(parameter, asset);
        Asset updatedAsset = assetRepository.save(asset);
        return new SuccessResponse(updatedAsset);
    }

    public SuccessResponse getMainAssets() {
        List<AssetDto> assets = assetRepository.findMainAssets()
                .orElseThrow(() -> new EntityNotFoundException("요청한 자원이 없습니다."));

        return new SuccessResponse(assets);
    }

    public SuccessResponse getMainAssetsByGenre(String genre) {
        List<AssetRawDto> assets = assetRepository.findMainAssetsByGenre(genre)
                .orElseThrow(() -> new EntityNotFoundException("요청한 자원이 없습니다."));

        return new SuccessResponse(assets);
    }

    public SuccessResponse getAssetsWithArtist() {
        List<Asset> assets = assetRepository.findAll();

        List<AssetRawDto> data = assets.stream()
                .map(assetMapper::toRawDto)
                .collect(Collectors.toList());

        return new SuccessResponse(data);
    }

    public SuccessResponse getAssetsByGenre(String genre) {
        List<AssetRawDto> assets = assetRepository.findAssetsByGenre(genre)
                .orElseThrow(() -> new EntityNotFoundException("요청한 장르와 일치하는 에셋이 없습니다."));

        return new SuccessResponse(assets);
    }

    public SuccessResponse getAssets(long artistId) {
        List<AssetRawDto> assets = assetRepository.findAllByArtistId(artistId)
                .orElseThrow(() -> new EntityNotFoundException("요청한 ArtistID와 일치하는 에셋이 없습니다."));

        return new SuccessResponse(assets);
    }

    public SuccessResponse getAssetsByArtistCode(String code) {
        Artist artist = artistRepository.findByCode(code)
                .orElseThrow(() -> new EntityNotFoundException("요청한 Code와 일치하는 아티스트가 없습니다."));

        List<AssetRawDto> assets = assetRepository.findAllByArtistId(artist.getId())
                .orElseThrow(() -> new EntityNotFoundException("요청한 ArtistID와 일치하는 에셋이 없습니다."));

        return new SuccessResponse(assets);
    }

    public SuccessResponse getAssetDetail(long assetId){
        Asset asset = assetRepository.findById(assetId)
                .orElseThrow(() -> new EntityNotFoundException("요청한 ID와 일치하는 에셋이 없습니다."));

        return new SuccessResponse(asset);
    }

    @Transactional
    public void deleteAsset(long assetId){
        Asset asset = assetRepository.findById(assetId)
                .orElseThrow(() -> new EntityNotFoundException("요청한 ID와 일치하는 에셋이 없습니다."));

        assetRepository.delete(asset);
    }

    public SuccessResponse registerAsset(String code, CreateAssetParameter parameter) {
        return null;
    }
}
