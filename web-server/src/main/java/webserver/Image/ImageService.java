package webserver.Image;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class ImageService {

    @Autowired
    private ResourceLoader resourceLoader;      //이미지용도

    public void postImage(long memberId, MultipartFile imgFile) throws IOException {
        Resource resource = resourceLoader.getResource("classpath:static/images/profile");
        String filePath =resource.getFile().getAbsolutePath();
        try {
            // 이미지 파일 이름은 학번으로만 두기
            imgFile.transferTo(new File(filePath+"/"+memberId));
        } catch(IOException e) {
            new File(filePath + "/" + memberId).delete();
        }
    }

}
