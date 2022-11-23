package com.www.backend.domain.artwork.dto;

import lombok.Getter;

@Getter
public class SearchArtworkRequest {
    private int page;
    private int take;

    public SearchArtworkRequest(int page, int take) {
        this.page = page;
        this.take = take;
    }
}
