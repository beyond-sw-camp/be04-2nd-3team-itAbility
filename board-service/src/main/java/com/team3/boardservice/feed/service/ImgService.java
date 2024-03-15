package com.team3.boardservice.feed.service;

import com.team3.boardservice.feed.dto.ImgDTO;
import com.team3.boardservice.feed.dto.ImgEnum;
import com.team3.boardservice.feed.repository.ImgRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Service
public class ImgService {
    private final ImgRepo imgRepo;

    private final ResourceLoader resourceLoader;

    @Autowired
    public ImgService(ImgRepo imgRepo, ResourceLoader resourceLoader) {
        this.imgRepo = imgRepo;
        this.resourceLoader = resourceLoader;
    }

    /* 멀티 파일 업로드 */
    public List<ImgDTO> multiFileUpload( List<MultipartFile> multiFiles) throws IOException {

        String filePath = resourceLoader.getResource("classpath:static/images/feed")
                .getFile()
                .getAbsolutePath();

        /* 설명. 사용자가 올린 파일들을 다 rename하고 저장하는 작업 및 DB로 전달하기 위한 List에 쌓기 */
        List<Map<String, String>> files = new ArrayList<>();    // DB에 저장할 값들을 지닌 List
        List<ImgDTO> imgDTOS = new ArrayList<>();
        for (int i = 0; i < multiFiles.size(); i++) {

            String originFileName = multiFiles.get(i).getOriginalFilename();
            String ext = originFileName.substring(originFileName.lastIndexOf("."));
            String savedName = UUID.randomUUID().toString().replace("-", "") + ext;

            /* 설명. DB에 저장할 사용자가 올린 하나의 파일 정보(DB 테이블 컬럼을 참고할 것) */
            Map<String, String> file = new HashMap<>();
            file.put("originFileName", originFileName);
            file.put("savedName", savedName);
            file.put("filePath", filePath);
            files.add(file);
            multiFiles.get(i).transferTo(new File(filePath + "/" + savedName));
            ImgDTO image =  new ImgDTO(savedName, ImgEnum.post,ext,filePath);

            imgDTOS.add(image);
            imgRepo.save(image);
        }
        return imgDTOS;
    }

    /* 멀티 파일 삭제*/
    public void removeImages(List<String> imgIds) {
        for (String imgId : imgIds) {
            imgRepo.deleteById(imgId);
        }
    }
}
