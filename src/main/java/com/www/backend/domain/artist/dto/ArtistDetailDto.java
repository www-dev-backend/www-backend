package com.www.backend.domain.artist.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.querydsl.core.annotations.QueryProjection;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ArtistDetailDto {
    private Long id;

    private String type;

    private String genre;

    private String name;

    private String nickname;

    private String email;

    private String contact;

    private String description;

    private String bio;

    private String profileImage;

    private String linkTree;


    @QueryProjection
    public ArtistDetailDto(Long id, String type, String genre, String name, String nickname, String email, String contact, String description, String bio, String profileImage, String linkTree) {
        this.id = id;
        this.type = type;
        this.genre = genre;
        this.name = name;
        this.nickname = nickname;
        this.email = email;
        this.contact = contact;
        this.description = description;
        this.bio = bio;
        this.profileImage = profileImage;
        this.linkTree = linkTree;
    }
}
