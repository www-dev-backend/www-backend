package com.www.backend.domain.artist.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.querydsl.core.annotations.QueryProjection;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ArtistDto {
    private Long id;
    private String genre;
    private String nickname;

    private String profileImage;


    @QueryProjection
    public ArtistDto(Long id, String genre, String nickname, String profileImage) {
        this.id = id;
        this.genre = genre;
        this.nickname = nickname;
        this.profileImage = profileImage;
    }
}
