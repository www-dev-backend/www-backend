package com.www.backend.domain.asset.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateAssetWrapper {
    private String email;

    private CreateAssetParameter asset;
}
