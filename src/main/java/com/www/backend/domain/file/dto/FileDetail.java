package com.www.backend.domain.file.dto;

import com.www.backend.common.util.MultipartUtils;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class FileDetail {
    private String id;
    private String name;
    private String format;
    private String path;
    private long bytes;
    private final LocalDateTime createdAt = LocalDateTime.now();

    public static FileDetail multipartOf(MultipartFile multipartFile) {
        final String fileId = MultipartUtils.createFileId();
        final String format = MultipartUtils.getFormat(multipartFile.getContentType());
        final String directory = MultipartUtils.createBaseDirectory(format);

        return FileDetail.builder()
                .id(fileId)
                .name(multipartFile.getOriginalFilename())
                .format(format)
                .path(MultipartUtils.createPath(directory, fileId, format))
                .bytes(multipartFile.getSize())
                .build();
    }
}
