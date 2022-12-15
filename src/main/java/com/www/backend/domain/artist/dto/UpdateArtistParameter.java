package com.www.backend.domain.artist.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter
public class UpdateArtistParameter {
    private String type;

    private String genre;

    private String name;

    private String nickname;

    @Email()
    private String email;

    private String contact;

    private String description;

    private String bio;

    private String profileImage;

    private String linkTree;
}
