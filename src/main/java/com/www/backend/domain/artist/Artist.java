package com.www.backend.domain.artist;

import com.www.backend.common.entity.BaseTimeEntity;
import com.www.backend.domain.asset.Asset;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;


@Entity
@Getter @Setter
@SQLDelete(sql = "update artists set deleted_at=current_timestamp where id=?")
@Table(name = "artists")
public class Artist extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//     TODO: 장르는 ENUM 타입으로 수정해야 함
    private String genre;

    private String name;

    private String email;

    private String instagramAccount;

    private String description;

    private String bio;

    private String profileImage;

    public void add(Asset asset) {
        asset.setArtist(this);
    }
}