package com.www.backend.domain.artist.dto;

import lombok.Data;
import lombok.Getter;

@Getter
public class SearchArtistRequest {
    private int page;
    private int take;

    public SearchArtistRequest(int page, int take) {
        this.page = page;
        this.take = take;
    }
}
