package com.www.backend.domain.artist.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class UpdateArtistParameter {
    private String genre;

    private String name;

    @Email()
    private String email;

    private String instagramAccount;

    private String description;

    private String bio;

    private String profileImage;
}
