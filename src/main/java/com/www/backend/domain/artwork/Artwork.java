package com.www.backend.domain.artwork;

import com.www.backend.common.entity.BaseTimeEntity;
import com.www.backend.domain.artist.Artist;
import com.www.backend.domain.asset.Asset;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@SQLDelete(sql = "update artworks set deleted_at=current_timestamp where id=?")
@Table(name = "artworks")
public class Artwork extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column(length = 1000)
    private String description;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "artist_id")
    private Artist artist;

    public void registerArtist(Artist artist) {
        this.artist = artist;
    }
}
