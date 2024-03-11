package com.team3.itability.feed.dto;

import com.team3.itability.img.enumData.IMG_USE;
import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "img_dto")
@Table(name = "image")
@ToString
public class ImgDTO {

    @Id
    @Column(name = "img_id")
    private String imgId;
    @Column(name = "img_use")
    @Enumerated(EnumType.STRING)
    private IMG_USE imgUse;
    @Column(name = "ext")
    private String ext;
    @Column(name = "path")
    private String path;


}
