package com.www.backend.domain.artist.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.querydsl.core.annotations.QueryProjection;
import com.querydsl.core.types.dsl.DateTimePath;
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

//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;

    // private List<Image> profileImages;


    @QueryProjection
    public ArtistDto(Long id, String genre, String name, String email, String instagramAccount, String description, String bio, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.genre = genre;
        this.name = name;
        this.email = email;
        this.instagramAccount = instagramAccount;
        this.description = description;
        this.bio = bio;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
