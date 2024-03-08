package com.team3.itability.img.entity;

import com.team3.itability.img.enumData.IMG_USE;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "image_dto")
@Table(name = "image")
@ToString
public class ImageEntity {

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


    public ImageEntity(Long imgId, String path, IMG_USE imgUse, String ext) {
        this.imgId = String.valueOf(imgId);
        this.path = path;
        this.imgUse = imgUse;
        this.ext = ext;
    }
}
