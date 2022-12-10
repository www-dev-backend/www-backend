package com.www.backend.domain.asset;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.www.backend.common.entity.BaseTimeEntity;
import com.www.backend.domain.artist.Artist;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;

import javax.persistence.*;

@Entity
@Getter @Setter
@Table(name = "assets")
public class Asset extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @Comment("Asset 장르")
    private String genre;

    @Column
    @Comment("Asset 타입(image, video)")
    private String type;

    @Column
    @Comment("Asset 접근 주소")
    private String url;

    @Column(columnDefinition="boolean default false")
    @Comment("대표 작품 여부")
    private Boolean isMain;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "artist_id", referencedColumnName = "id")
    @Comment("FK: 아티스트 아이디")
    private Artist artist;
}
