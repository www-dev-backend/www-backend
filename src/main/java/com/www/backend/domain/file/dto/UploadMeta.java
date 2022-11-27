package com.www.backend.domain.file.dto;

import lombok.Getter;

@Getter
public class UploadMeta {
    private String url;
    private FileDetail detail;

    public UploadMeta(String url, FileDetail detail) {
        this.url = url;
        this.detail = detail;
    }
}
