package com.team3.memberservice.img;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.*;
import com.team3.memberservice.img.dao.ImageDAO;
import com.team3.memberservice.img.dto.GoogleImageDTO;
import com.team3.memberservice.img.dto.ImageDTO;
import com.team3.memberservice.img.entity.ImageEntity;
import com.team3.memberservice.member.dao.MemberInfoRepo;
import com.team3.memberservice.mypage.dao.MemberProfileDAO;
import com.team3.memberservice.mypage.dto.MemberProfileDTO;
import com.team3.memberservice.mypage.entity.MemberProfileEntity;
import com.team3.memberservice.mypage.service.MypageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class ImageService {

    @Value("${spring.cloud.gcp.storage.bucket}") // application.yml에 써둔 bucket 이름
    private  String bucketName;
    @Value("${spring.cloud.gcp.storage.project-id}")
    private  String projectId;
    @Value("${spring.cloud.gcp.storage.credentials.location}")
    private String credentialsLocation;
    private  Storage storage;

    private final MemberProfileDAO memberProfileDAO;
    private final ImageDAO imageDAO;

    @Autowired
    public ImageService(MemberProfileDAO memberProfileDAO, ImageDAO imageDAO) {
        this.memberProfileDAO = memberProfileDAO;
        this.imageDAO = imageDAO;
    }

    public String uploadImage(Long memberId, MultipartFile file) throws IOException {
        ClassPathResource resource = new ClassPathResource(credentialsLocation);
        GoogleCredentials credentials = GoogleCredentials.fromStream(resource.getInputStream());

        Storage storage = StorageOptions.newBuilder()
                .setCredentials(credentials)
                .setProjectId(projectId)
                .build()
                .getService();

        String fileName = generateFileName(file.getOriginalFilename());
        BlobId blobId = BlobId.of(bucketName, fileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();

        byte[] bytes = file.getBytes();
        Blob blob = storage.create(blobInfo, bytes);
        String returnValue = blob.getMediaLink();

        MemberProfileEntity memberProfile = memberProfileDAO.findById(memberId).orElseThrow();
        ImageEntity img = new ImageEntity();
        img.setImgId(memberId+"");
        img.setPath(returnValue);
        img = imageDAO.save(img);
        memberProfile.setImg(img);
        return returnValue;
    }
    private String generateFileName(String originalName) {
        // 이미지 파일 이름을 랜덤 UUID로 생성
        return UUID.randomUUID().toString();
    }

    public ImageEntity getProfileImage(long memberId) {
        ImageEntity image = imageDAO.findById(memberId).orElseThrow();
        return image;
    }
}
