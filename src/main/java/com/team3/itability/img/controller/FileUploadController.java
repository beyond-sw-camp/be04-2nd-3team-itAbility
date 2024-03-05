package com.team3.itability.img.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Controller
public class FileUploadController {
    /*설명. 빌드된 파일 업로드 할 경로를 가져오기 위해 의존성 주입 받기 위함*/
    @Autowired
    private ResourceLoader resourceLoader;

    @PostMapping("single-file")
    public String singleFileUpload(@RequestParam MultipartFile singleFile, @RequestParam String singleFileDescription
            , RedirectAttributes rttr) throws IOException {

        /*encType= "multipart-form-data" 형태로 넘어 온 파일은 MultipartFile 타입으로 받게 된다. */
        System.out.println("singleFile= "+ singleFile);
        System.out.println("singleFileDescription = " + singleFileDescription);

        /*1. build 경로의 static에 있는 파일 업로드 할 곳의 경로를 받아온다.(미리 경로에 해당하는 디렉토리 생성 및 빌드 확인할 것)*/
        Resource resource = resourceLoader.getResource("classpath:static/uploadFiles/img/single");
        //System.out.println("빌드된 싱글 디렉토리의 절대 경로: "+ resource.getFile().getAbsolutePath() );
        String filePath =resource.getFile().getAbsolutePath();

        /*2. 사용자가 넘긴 파일을 확인하고, rename 해보기 (날짜로 이름을 변경하는 방식과 랜덤 uuid를 만드는 방식이 있는데 후자 방식으로 하는 예시)*/
        String originFileName= singleFile.getOriginalFilename();
        System.out.println("originFileName = " + originFileName);
        String ext = originFileName.substring(originFileName.lastIndexOf(".")); //설명. '.png' 만 받기 위함

        System.out.println("ext = " + ext);
        if(!".png".equals(ext)){
            rttr.addFlashAttribute("singleFileDescription", "파일 형식 불일치");
            return "redirect:/result";
        }
        String saveName = UUID.randomUUID().toString().replace("-","")+ext; //설명. UUID 랜덤은 중간중간 '-'가 있어 제거 작업함.
        System.out.println("saveName = " + saveName);

        /*3. 지정한 경로로 파일 저장하고 */
        try {
            singleFile.transferTo(new File(filePath+"/"+saveName));

            /*설명. 디비를 다녀오는 BL 구문 작성(디비에 저장)
                   비즈니스 로직이 성공하면 화면의 재료를 RedirectAttributes로 담는다(flashAttribute로 담기)*/
            rttr.addFlashAttribute("message","파일 업로드 성공");
            rttr.addFlashAttribute("img","uploadFiles/img/single/"+saveName);
            rttr.addFlashAttribute("singleFileDescription",singleFileDescription);

        } catch(IOException e){
            /*설명. try 구문 안에서(디비를 다녀오는 비즈니스 로직 처리) 예외가 발생하면 파일만 올라가면 안되므로 찾아서 다시 지워줌*/
            new File(filePath+"/"+saveName).delete();
        }

        return "redirect:/result";  //설명. 계속 새로고침하여 insert되는 것을 막기 위함!!
    }

    @PostMapping("multi-file")
    public String multiFileUpload(@RequestParam List<MultipartFile> multiFiles
            , @RequestParam String multiFileDescription, RedirectAttributes rttr) throws IOException {

        String filePath = resourceLoader.getResource("classpath:static/images").getFile().getAbsolutePath();

        /*사용자가 올린 파일들을 다 rename하고 저장하는 작업 및 디비로 전달하기 위한 List에 쌓기*/
        List<Map<String,String>>files = new ArrayList<>(); // DB에 저장할 값들을 지닌 List
        List<String> saveFiles = new ArrayList<>(); /*화면에서 불러올 이미지 경로들(src 경로)*/
        try {
            for(int i=0; i< multiFiles.size();i++){
                String originFileName = multiFiles.get(i).getOriginalFilename();
                String ext = originFileName.substring(originFileName.lastIndexOf("."));
                String savedName = UUID.randomUUID().toString().replace("-","")+ext;

                /*DB에 저장할 사용자가 올린 하나의 파일 정보(DB 테이블 컬럼 참고할 것)*/
                Map<String, String>file = new HashMap<>();
                file.put("originFileName", originFileName);
                file.put("savedName", savedName);
                file.put("filePath",filePath);

                files.add(file);

                multiFiles.get(i).transferTo(new File(filePath+"/"+savedName));

                saveFiles.add("images"+savedName);
            }
            /*성공했다면 화면의 재료를 던져준다.*/
            rttr.addFlashAttribute("imgs", saveFiles);

        }catch (Exception e){
            /* List<Map<String,String>> files로 쌓인 업로드된 파일들을 찾아 일일이 다시 지운다. */
            for(int i=0; i<files.size();i++){
                Map<String, String> file = files.get(i);
                new File(filePath+"/"+file.get("savedName")).delete();
            }
            /*실패 시 전달할 메시지*/
        }
        return "redirect:/result";
    }

    @GetMapping("/result")
    public void result(){}

}
