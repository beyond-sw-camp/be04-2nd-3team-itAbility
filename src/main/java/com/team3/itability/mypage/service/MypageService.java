package com.team3.itability.mypage.service;

import com.team3.itability.mypage.dao.*;
import com.team3.itability.mypage.dto.CareerDTO;
import com.team3.itability.mypage.dto.DegreeDTO;
import com.team3.itability.mypage.dto.ImageDTO;
import com.team3.itability.mypage.dto.MemberProfileDTO;
import com.team3.itability.mypage.enumData.IMG_USE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service

public class MypageService {
    MemberProfileDAO memberProfileDAO;
    DegreeDAO degreeDAO;
    ImageDAO imageDAO;
    CareerDAO careerDAO;
    MemberSkillDAO memberSkillDAO;
    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    public MypageService(MemberProfileDAO memberProfileDAO, DegreeDAO degreeDAO, ImageDAO imageDAO, CareerDAO careerDAO, MemberSkillDAO memberSkillDAO) {
        this.memberProfileDAO = memberProfileDAO;
        this.degreeDAO = degreeDAO;
        this.imageDAO = imageDAO;
        this.careerDAO = careerDAO;
        this.memberSkillDAO = memberSkillDAO;
    }



    /** <h1> 1. 자신의 마이페이지 정보 조회</h1> */
    @Transactional
    public MemberProfileDTO printMypageData(long memberId){

        MemberProfileDTO memberProfileDTO =memberProfileDAO.findById(memberId).orElseThrow();
        if(memberProfileDTO.getImg()==null){
            ImageDTO image = new ImageDTO();
            image.setImgId(memberProfileDTO.getMemberId()+"");
            image.setExt("link");
            image.setPath("none");
            image.setImgUse(IMG_USE.profile);
            image =  imageDAO.save(image);
            System.out.println("사진 저장 image = " + image);
            memberProfileDTO.setImg(image);
            System.out.println("사진 저장 memberProfileDTO = " + memberProfileDTO.getImg());
        }
        System.out.println("memberProfileDTO = " + memberProfileDTO);

        return memberProfileDTO;
    }

    /** <h1>2. 마이페이지 수정</h1>*/
    @Transactional
    public MemberProfileDTO modifyMypage(long memberId, String nickname, String name, String phone, String birthdate) {
        MemberProfileDTO memberProfileDTO = memberProfileDAO.findById(memberId).orElseThrow();
        memberProfileDTO.setNickname(nickname);
        memberProfileDTO.getMemberInfo().setName(name);
        memberProfileDTO.getMemberInfo().setPhone(phone);
        memberProfileDTO.getMemberInfo().setBirthDate(birthdate);
        return memberProfileDTO;
    }
    /**
     * <h1>3. 학력 수정</h1>
     * */
    @Transactional
    public MemberProfileDTO modifyDegree(long memberId, DegreeDTO degreeDTO) {

        MemberProfileDTO memberProfileDTO = memberProfileDAO.findById(memberId).orElseThrow();
        memberProfileDTO.getDegree().setMajor(degreeDTO.getMajor());
        memberProfileDTO.getDegree().setEnrollDate(degreeDTO.getEnrollDate());
        memberProfileDTO.getDegree().setGraduateDate(degreeDTO.getGraduateDate());
        memberProfileDTO.getDegree().setFinalEduName(degreeDTO.getFinalEduName());
        memberProfileDTO.getDegree().setRegisterStatus(degreeDTO.isRegisterStatus());
        System.out.println("memberProfileDTO = " + memberProfileDTO.getDegree());
        /*egree=DegreeDTO{degreeId=1, finalEduName='서울대학교', enrollDate='2010-03-01', graduateDate='2014-02-28', major='컴퓨터 공학', registerStatus=false*/
        return memberProfileDTO;
    }

    /**<h1>4. 경력 조회,수정,추가</h1>*/
    @Transactional(readOnly = true)
    public List<CareerDTO> printCareerList(long memberId){
        MemberProfileDTO memberProfileDTO = memberProfileDAO.findById(memberId).orElseThrow();

        return careerDAO.findByMemberId(memberProfileDTO);
    }
    @Transactional(readOnly = true)
    public CareerDTO printCareer(int careerId) {
        return careerDAO.findById(careerId).orElseThrow();
    }
    @Transactional
    public CareerDTO modifyCareer(CareerDTO careerDTO) {
        CareerDTO career = careerDAO.findById(careerDTO.getCareerId()).orElseThrow();
        career.setCompanyName(careerDTO.getCompanyName());
        career.setStartDate(career.getStartDate());
        career.setEndDate(career.getEndDate());
        career.setRole(career.getRole());
        career.setAssignedTask(career.getAssignedTask());
        career.setCurrentJob(career.isCurrentJob());
        return career;
    }

    @Transactional
    public CareerDTO addCareer(CareerDTO careerDTO, long memberId) {
        MemberProfileDTO member = memberProfileDAO.findById(memberId).orElseThrow();
        careerDTO.setMemberId(member);
        careerDAO.save(careerDTO);
        return careerDTO;
    }

    public ImageDTO getImageDTO(long memberId) {
        MemberProfileDTO member = memberProfileDAO.findById(memberId).orElseThrow();

        return member.getImg();
    }

    @Transactional
    public ImageDTO modifyImageDTO(long memberId, MultipartFile imgFile) throws IOException {

        MemberProfileDTO member = memberProfileDAO.findById(memberId).orElseThrow();
        ImageDTO image = member.getImg();

        /*1. build 경로의 static에 있는 파일 업로드 할 곳의 경로를 받아온다.(미리 경로에 해당하는 디렉토리 생성 및 빌드 확인할 것)*/
        Resource resource = resourceLoader.getResource("classpath:static/images/profile");
        String filePath =resource.getFile().getAbsolutePath();

        /*2. 사용자가 넘긴 파일을 확인하고, rename 해보기 (날짜로 이름을 변경하는 방식과 랜덤 uuid를 만드는 방식이 있는데 후자 방식으로 하는 예시)*/
        String originFileName= imgFile.getOriginalFilename();
        String ext = originFileName.substring(originFileName.lastIndexOf("."));

        new File(filePath+"/"+image.getImgId()+image.getExt()).delete();

        String saveName = memberId+ext;
        /*3. 지정한 경로로 파일 저장 */
        try {
            imgFile.transferTo(new File(filePath+"/"+saveName));
            image.setExt(ext);
            image.setImgUse(IMG_USE.profile);
            image.setPath("/images/profile/"+saveName);

        } catch(IOException e){
            /*설명. try 구문 안에서(디비를 다녀오는 비즈니스 로직 처리) 예외가 발생하면 파일만 올라가면 안되므로 찾아서 다시 지워줌*/
            new File(filePath+"/"+saveName).delete();
        }

        return member.getImg();
    }
}
