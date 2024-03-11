package com.team3.itability.img.dto;

import com.team3.itability.img.enumData.IMG_USE;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ImageDTO {
    @Column(name = "img_id")
    private String imgId;
    private IMG_USE imgUse;
    private String ext;
    private String path;

    public ImageDTO(Long imgId, String path, IMG_USE imgUse, String ext) {
        this.imgId = String.valueOf(imgId);
        this.path = path;
        this.imgUse = imgUse;
        this.ext = ext;
    }
}
