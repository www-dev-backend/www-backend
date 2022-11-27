package com.www.backend.domain.asset.dto;

import lombok.Getter;

@Getter
public class SearchAssetRequest {
    private int page;
    private int take;

    public SearchAssetRequest(int page, int take) {
        this.page = page;
        this.take = take;
    }
}
