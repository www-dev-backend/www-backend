package com.www.backend.domain.artist.dto;

import com.www.backend.domain.asset.dto.AssetDto;
import com.www.backend.domain.asset.dto.AssetRawDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class ArtistWrapperDto {
    private Long id;
    private String code;
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
    private List<AssetRawDto> assets;

    public ArtistWrapperDto(ArtistDetailDto artist, List<AssetRawDto> assets) {
        this.id = artist.getId();
        this.code = artist.getCode();
        this.type = artist.getType();
        this.genre = artist.getGenre();
        this.name = artist.getName();
        this.nickname = artist.getNickname();
        this.email = artist.getEmail();
        this.contact = artist.getContact();
        this.description = artist.getDescription();
        this.bio = artist.getBio();
        this.profileImage = artist.getProfileImage();
        this.linkTree = artist.getLinkTree();
        this.assets = assets;
    }
}
