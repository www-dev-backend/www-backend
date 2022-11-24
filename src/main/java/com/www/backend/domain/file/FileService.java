package com.www.backend.domain.file;

import com.www.backend.common.response.SuccessResponse;
import com.www.backend.common.util.AmazonS3Utils;
import com.www.backend.domain.file.dto.FileDetail;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class FileService {
    private final AmazonS3Utils amazonS3Utils;

    public SuccessResponse save(MultipartFile file) {
        FileDetail fileDetail = FileDetail.multipartOf(file);
        amazonS3Utils.store(fileDetail.getPath(), file);
        return new SuccessResponse(fileDetail);
    }
}
