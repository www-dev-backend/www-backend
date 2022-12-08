package com.www.backend.domain.file;

import com.www.backend.common.response.SuccessResponse;
import com.www.backend.common.util.AmazonS3Utils;
import com.www.backend.domain.file.dto.FileDetail;
import com.www.backend.domain.file.dto.UploadMeta;
import com.www.backend.domain.file.mapper.FileMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class FileService {
    private final FileRepository fileRepository;

    private final FileMapper fileMapper;
    private final AmazonS3Utils amazonS3Utils;

    public SuccessResponse uploadAsset(MultipartFile file) {
        FileDetail fileDetail = FileDetail.multipartOf(file);
        String url = amazonS3Utils.store(fileDetail.getPath(), file);

        fileRepository.save(fileMapper.toEntity(fileDetail));

        return new SuccessResponse(new UploadMeta(url, fileDetail));
    }
}
