package com.www.backend.domain.asset.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter @Setter
public class CreateAssetParameter {
    @NotBlank(message = "작품 Genre는 필수 값입니다.")
    private String genre;

    @NotBlank(message = "작품 Type은 필수 값입니다.")
    private String type;

    @NotBlank(message = "URL은 필수 값입니다.")
    private String url;

    @NotBlank(message = "대표 작품 여부는 필수 값입니다.")
    private Boolean isMain;

    public CreateAssetParameter(String genre, String type, String url, Boolean isMain) {
        this.genre = genre;
        this.type = type;
        this.url = url;
        this.isMain = isMain;
    }
}
