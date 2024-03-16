package com.team3.boardservice.feed.dto;

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
    private ImgEnum imgUse;
    @Column(name = "ext")
    private String ext;
    @Column(name = "path")
    private String path;


}
