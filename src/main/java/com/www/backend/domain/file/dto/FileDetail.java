package com.www.backend.domain.file.dto;

import com.www.backend.common.util.MultipartUtils;
import com.www.backend.config.AWSConfig;
import lombok.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;


@Getter
@Configuration
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class FileDetail {
    private String id;
    private String fileType;
    private String name;
    private String format;
    private String path;
    private String url;
    private long size;
    private int width;
    private int height;
    private final LocalDateTime createdAt = LocalDateTime.now();

    public static FileDetail multipartOf(MultipartFile multipartFile) {
        final String fileId = MultipartUtils.createFileId();
        final String format = MultipartUtils.getFormat(multipartFile.getContentType());
        final String fileType = MultipartUtils.createFileType(format);
        final Resolution resolution = MultipartUtils.getResolution(fileType, multipartFile);

        return FileDetail.builder()
                .id(fileId)
                .fileType(fileType)
                .name(multipartFile.getOriginalFilename())
                .format(format)
                .path(MultipartUtils.createPath(fileType, fileId, format))
                .url(MultipartUtils.createUrl(fileType, fileId, format))
                .size(multipartFile.getSize())
                .width(resolution.getWidth())
                .height(resolution.getHeight())
                .build();
    }
}
