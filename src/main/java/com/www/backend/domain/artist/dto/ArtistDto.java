package com.www.backend.domain.artist.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.querydsl.core.annotations.QueryProjection;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ArtistDto {
    private Long id;

    private String genre;

    private String name;

    private String email;

    private String instagramAccount;

    private String description;

    private String bio;


    @QueryProjection
    public ArtistDto(Long id, String genre, String name, String email, String instagramAccount, String description, String bio) {
        this.id = id;
        this.genre = genre;
        this.name = name;
        this.email = email;
        this.instagramAccount = instagramAccount;
        this.description = description;
        this.bio = bio;
    }
}
