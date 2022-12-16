package com.www.backend.domain.asset.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter @Setter
public class AssetRawDto {
    @NotBlank(message = "작품 Genre는 필수 값입니다.")
    private String genre;

    @NotBlank(message = "작품 Type은 필수 값입니다.")
    private String type;

    @NotBlank(message = "URL은 필수 값입니다.")
    private String url;

    @NotBlank(message = "대표 작품 여부는 필수 값입니다.")
    private Boolean isMain;

    @QueryProjection
    public AssetRawDto(String genre, String type, String url, Boolean isMain) {
        this.genre = genre;
        this.type = type;
        this.url = url;
        this.isMain = isMain;
    }
}
