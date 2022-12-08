package com.www.backend.domain.file;

import com.www.backend.common.entity.BaseTimeEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter @Setter
@Table(name = "files")
public class File extends BaseTimeEntity {
    @Id
    private UUID id;

    @Comment("파일 타입(image, video)")
    @Column
    private String fileType;

    @Comment("원본 파일 이름")
    @Column
    private String name;

    @Comment("파일 포맷")
    @Column
    private String format;

    @Comment("파일 경로")
    @Column
    private String path;

    @Comment("파일 S3 접근 경로")
    @Column
    private String url;

    @Comment("파일 사이즈")
    @Column
    private String size;

    @Comment("이미지 또는 비디오의 Width")
    @Column
    private String width;

    @Comment("이미지 또는 비디오의 Height")
    @Column
    private String height;
}
