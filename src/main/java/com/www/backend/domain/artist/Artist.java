package com.www.backend.domain.artist;

import com.www.backend.common.entity.BaseTimeEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Getter @Setter
@Table(name = "artists")

public class Artist extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // TODO: 장르는 ENUM 타입으로 수정해야 함
    private String genre;

    private String name;

    private String email;

    private String instagramAccount;

    private String description;

    private String bio;

    private String profileImage;

//    @OneToMany
//    JoinColumn(name = "artist_id")
//    private List<Image> profileImages;
}