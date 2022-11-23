package com.www.backend.domain.genre;

public enum EGenre {
    VISUAL("visual"),
    MEDIA("media"),
    FASHION("fashion"),
    LIVING("living");

    private String genre;

    EGenre(String genre) {
        this.genre = genre;
    }

    public String getGenre() {
        return genre;
    }
}
