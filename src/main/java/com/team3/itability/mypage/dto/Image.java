package com.team3.itability.mypage.dto;

import com.team3.itability.mypage.enumData.IMG_USE;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Image {
    @Column(name = "img_id")
    private String imgId;
    private IMG_USE imgUse;
    private String ext;
    private String path;

    public Image(Long imgId, String path, IMG_USE imgUse, String ext) {
        this.imgId = String.valueOf(imgId);
        this.path = path;
        this.imgUse = imgUse;
        this.ext = ext;
    }
}
