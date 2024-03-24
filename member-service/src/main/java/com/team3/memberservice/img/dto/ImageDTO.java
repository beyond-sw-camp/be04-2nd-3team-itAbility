package com.team3.memberservice.img.dto;

import com.team3.memberservice.img.enumData.IMG_USE;
import jakarta.persistence.Column;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ImageDTO {
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
