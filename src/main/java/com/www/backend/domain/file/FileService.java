package com.www.backend.domain.file;

import com.www.backend.common.exceptions.EntityNotFoundException;
import com.www.backend.common.response.SuccessResponse;
import com.www.backend.common.util.AmazonS3Utils;
import com.www.backend.domain.artist.Artist;
import com.www.backend.domain.artist.ArtistRepository;
import com.www.backend.domain.artist.mapper.ArtistMapper;
import com.www.backend.domain.asset.Asset;
import com.www.backend.domain.asset.AssetRepository;
import com.www.backend.domain.asset.AssetService;
import com.www.backend.domain.asset.dto.CreateAssetParameter;
import com.www.backend.domain.asset.mapper.AssetMapper;
import com.www.backend.domain.file.dto.FileDetail;
import com.www.backend.domain.file.dto.UploadMeta;
import com.www.backend.domain.file.mapper.FileMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FileService {
    private final FileRepository fileRepository;
    private final FileMapper fileMapper;
    private final AssetService assetService;
    private final ArtistRepository artistRepository;
    private final ArtistMapper artistMapper;
    private final AmazonS3Utils amazonS3Utils;

    public SuccessResponse uploadAsset(MultipartFile file) {
        FileDetail fileDetail = FileDetail.multipartOf(file);
        String url = amazonS3Utils.store(fileDetail.getPath(), file);

        fileRepository.save(fileMapper.toEntity(fileDetail));

        return new SuccessResponse(new UploadMeta(url, fileDetail));
    }

    @Transactional
    public SuccessResponse uploadAssets(String code, List<MultipartFile> files) {
        List<UploadMeta> metadata = new ArrayList<UploadMeta>();

        files.forEach((file) -> {
            FileDetail fileDetail = FileDetail.multipartOf(file);
            String url = amazonS3Utils.store(fileDetail.getPath(), file);

            fileRepository.save(fileMapper.toEntity(fileDetail));

            metadata.add(new UploadMeta(url, fileDetail));

            Artist artist = artistRepository.findByCode(code)
                    .orElseThrow(() -> new EntityNotFoundException("요청한 Code와 일치하는 아티스트가 없습니다."));

            CreateAssetParameter parameter = new CreateAssetParameter(artist.getGenre(), fileDetail.getFileType(), fileDetail.getUrl(), false);
            assetService.createAsset(code, parameter);
        });

        return new SuccessResponse(metadata);
    }
}
















