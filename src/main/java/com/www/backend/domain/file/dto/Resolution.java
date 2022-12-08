package com.www.backend.domain.file.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class Resolution {
    private int width = 0;
    private int height = 0;

    public Resolution(int width, int height) {
        this.width = width;
        this.height = height;
    }
}
