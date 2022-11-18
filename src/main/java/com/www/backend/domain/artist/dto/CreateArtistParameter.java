package com.www.backend.domain.artist.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class CreateArtistParameter {
    @NotEmpty()
    private String genre;

    @NotEmpty()
    private String name;

    @Email()
    @NotEmpty()
    private String email;

    @NotBlank()
    private String instagramAccount;

    @NotBlank()
    private String description;

    @NotBlank()
    private String bio;

    private String profileImage;
}
