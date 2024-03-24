package com.team3.memberservice.img.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GoogleImageDTO {

    private String nickname;
    private MultipartFile image; //!!!!!!!!!이미지 업로드 관련 부분

}