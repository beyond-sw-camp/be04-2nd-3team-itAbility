package com.team3.memberservice.img;

import com.team3.memberservice.img.dto.ImageDTO;
import com.team3.memberservice.img.entity.ImageEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/image")
public class ImageController {

    private final ImageService imageService;

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping("/profile/{memberId}")
    public ResponseEntity<ImageEntity> get(@PathVariable long memberId){
        ImageEntity imageUrl = imageService.getProfileImage(memberId);

        return ResponseEntity.ok().body(imageUrl);
    }


    @PatchMapping("/profile/{memberId}")
    public ResponseEntity<String> get(@PathVariable Long memberId, @RequestParam("file")MultipartFile file) throws IOException {
        System.out.println("프로필 이미지 업로드 요청");
        String imageUrl = imageService.uploadImage(memberId, file);
//        return ResponseEntity.status(HttpStatus.CREATED).body(imageUrl);
        System.out.println("imageUrl = " + imageUrl);

        return ResponseEntity.status(HttpStatus.OK).body(imageUrl);
    }

}
