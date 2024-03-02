package com.team3.itability.mypage.dto;

import com.team3.itability.mypage.enumData.IMG_USE;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "image_dto")
@Table(name = "image")
@ToString
public class ImageDTO {

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

    public ImageDTO() {
    }

    public ImageDTO(String imgId, IMG_USE imgUse, String ext, String path) {
        this.imgId = imgId;
        this.imgUse = imgUse;
        this.ext = ext;
        this.path = path;
    }

    public String getImgId() {
        return imgId;
    }

    public void setImgId(String imgId) {
        this.imgId = imgId;
    }

    public IMG_USE getImgUse() {
        return imgUse;
    }

    public void setImgUse(IMG_USE imgUse) {
        this.imgUse = imgUse;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "ImageDTO{" +
                "imgId='" + imgId + '\'' +
                ", imgUse=" + imgUse +
                ", ext='" + ext + '\'' +
                ", path='" + path + '\'' +
                '}';
    }
}
