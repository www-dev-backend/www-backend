package com.www.backend.domain.genre;

import com.www.backend.common.entity.BaseTimeEntity;
import com.www.backend.common.Enum.EGenre;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@Table(name = "genres")
public class Genre extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "genre_name", nullable = false)
    @Enumerated(EnumType.STRING)
    private EGenre genre;
}
