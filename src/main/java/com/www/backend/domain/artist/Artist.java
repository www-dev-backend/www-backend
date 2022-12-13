package com.www.backend.domain.artist;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.www.backend.common.entity.BaseTimeEntity;
import com.www.backend.common.util.CodeUtils;
import com.www.backend.domain.asset.Asset;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter @Setter
@SQLDelete(sql = "update artists set deleted_at=current_timestamp where id=?")
@Table(name = "artists")
public class Artist extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String code;

    @Comment("Personal or Team 구분")
    private String type;

    // TODO: 장르는 ENUM 타입으로 수정해야 함
    private String genre;

    private String name;

    private String nickname;
    @Column(unique = true)
    private String email;

    private String contact;

    private String description;

    @Lob
    @Comment(value = "자기소개")
    private String bio;

    private String profileImage;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "artist_id", referencedColumnName = "id")
    private List<Asset> assets = new ArrayList<>();

    public Asset add(Asset asset) {
        asset.setArtist(this);
        return asset;
    }

    public void register(Asset asset) {
        asset.setArtist(this);
    }

    public void createCode() {
        String[] tokens = this.email.split("@");
        String code = CodeUtils.generateCode(4);

        this.code = tokens[0] + "_" + code;
    }
}