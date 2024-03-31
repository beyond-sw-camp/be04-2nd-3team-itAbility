package com.team3.memberservice.mypage.service;

import com.team3.memberservice.career.dao.CareerDAO;
import com.team3.memberservice.career.dto.CareerDTO;
import com.team3.memberservice.client.MemberServiceClient;
import com.team3.memberservice.ftp.FTP_SERVER;
import com.team3.memberservice.img.ImageVO;
import com.team3.memberservice.img.dao.ImageDAO;
import com.team3.memberservice.img.dto.ImageDTO;
import com.team3.memberservice.img.entity.*;
import com.team3.memberservice.img.enumData.*;
import com.team3.memberservice.member.dao.*;
import com.team3.memberservice.member.dto.MemberInfoDTO;
import com.team3.memberservice.mypage.dao.*;
import com.team3.memberservice.mypage.dto.*;
import com.team3.memberservice.mypage.entity.*;
import com.team3.memberservice.skill.dao.MemberSkillDAO;
import com.team3.memberservice.skill.dao.SkillDAO;
import com.team3.memberservice.skill.dto.RequestSkillId;
import com.team3.memberservice.skill.dto.ResponseSkill;
import com.team3.memberservice.skill.dto.ResponseSkillList;
import com.team3.memberservice.skill.entity.MemberSkillEntity;
import com.team3.memberservice.skill.entity.MemberSkillId;
import com.team3.memberservice.skill.entity.SkillEntity;
import com.team3.memberservice.skill.service.SkillService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class MypageService {
    private final MemberProfileDAO memberProfileDAO;
    private final DegreeDAO degreeDAO;
    private final ImageDAO imageDAO;
    private final CareerDAO careerDAO;
    private final MemberSkillDAO memberSkillDAO;
    @Autowired
    private ResourceLoader resourceLoader;      //이미지용도
    private final SkillDAO skillDAO;

    private ModelMapper modelMapper;
    @Autowired
    private MemberInfoRepo memberInfoRepo;

    private MemberServiceClient client;
    private SkillService skillService;

    @Autowired
    public MypageService(MemberProfileDAO memberProfileDAO, DegreeDAO degreeDAO, ImageDAO imageDAO, CareerDAO careerDAO, MemberSkillDAO memberSkillDAO, SkillDAO skillDAO,
                         ModelMapper modelMapper,
                         MemberServiceClient client, SkillService skillService)
    {
        this.memberProfileDAO = memberProfileDAO;
        this.degreeDAO = degreeDAO;
        this.imageDAO = imageDAO;
        this.careerDAO = careerDAO;
        this.memberSkillDAO = memberSkillDAO;
        this.skillDAO = skillDAO;
        this.modelMapper = modelMapper;
        this.client= client;
        this.skillService= skillService;
    }



    /** <h1> 1. 자신의 마이페이지 정보 조회 - fin</h1> */
    @Transactional
    public MemberProfileDTO getMypage(long memberId){
        MemberProfileEntity memberProfileEntity =memberProfileDAO.findById(memberId).orElseThrow();
        if(memberProfileEntity.getImg()==null){
            ImageEntity image = new ImageEntity(memberProfileEntity.getMemberId()+"",IMG_USE.profile,"link","none" );
            image =  imageDAO.save(image);
            memberProfileEntity.setImg(image);
        }
        return modelMapper.map(memberProfileEntity, MemberProfileDTO.class);
    }

    /**<h1>2.경력조회</h1>*/
    private static CareerEntity getCareerDTO(CareerDTO careerDTO) {
        return new CareerEntity(careerDTO.getCareerId(),careerDTO.getCompanyName(), careerDTO.getStartDate(),
                careerDTO.getEndDate(), careerDTO.getRole(), careerDTO.getAssignedTask(), careerDTO.isCurrentJob(),
                careerDTO.getMemberId());
    }


    /**<h1>4.이미지수정</h1>*/
    public ImageDTO getImage(long memberId) {
        MemberProfileEntity member = memberProfileDAO.findById(memberId).orElseThrow();
        return modelMapper.map(member.getImg(), ImageDTO.class);
    }

    @Transactional
    public void modifyImageDTO(long memberId, MultipartFile imgFile) throws IOException {
        MemberProfileEntity member = memberProfileDAO.findById(memberId).orElseThrow();
        ImageEntity image = member.getImg();
        Resource resource = resourceLoader.getResource("classpath:static/images/profile");
        String filePath =resource.getFile().getAbsolutePath();
        String originFileName= imgFile.getOriginalFilename();
        String ext = originFileName.substring(originFileName.lastIndexOf("."));
        new File(filePath+"/"+image.getImgId()+image.getExt()).delete();
        String saveName = memberId+ext;
        try {
            imgFile.transferTo(new File(filePath+"/"+saveName));
            image= new ImageEntity(member.getMemberId(),"/images/profile/"+saveName,IMG_USE.profile,ext);
            imageDAO.save(image);
        } catch(IOException e){
            new File(filePath+"/"+saveName).delete();
        }
    }




    /**<h1>5.맴버기술조회</h1>*/
    public List<ResponseSkill> getMemberSkill(Long memberId) {

        List<MemberSkillEntity> memberSkills = memberSkillDAO.findByIdMemberId(memberId);
        List<ResponseSkill> responseSkills = skillService.findSkillList(memberSkills);
        return responseSkills;
    }
    /**<h1>맴버info,profile수정</h1>*/
    @Transactional
    public MemberProfileDTO PostMypage(RequestMember member, Long memberId) {
        MemberProfileEntity memberProfileEntity = memberProfileDAO.findById(memberId).orElseThrow();
        memberProfileEntity.setNickname(member.getNickname());
        MemberInfoDTO memberInfoDTO = memberProfileEntity.getMemberInfo();
        memberInfoDTO.update(member.getName(),member.getPhone(), member.getBirthDate());
        memberInfoRepo.save(memberInfoDTO);
        return modelMapper.map(memberProfileEntity,MemberProfileDTO.class);
    }
    /**<h1>경력</h1>*/
    @Transactional
    public ResponseDegree postDegree(long memberId, DegreeDTO degreeDTO) {
        MemberProfileEntity member = memberProfileDAO.findById(memberId).orElseThrow();
        DegreeEntity degree = member.getDegree();
        degree.update(degreeDTO.getFinalEduName(), degreeDTO.getEnrollDate(),
                degreeDTO.getGraduateDate() , degreeDTO.getMajor(), degreeDTO.isRegisterStatus() );

        ResponseDegree response= new ResponseDegree(modelMapper.map(member,MemberProfileDTO.class)
                                    ,modelMapper.map(degree,DegreeDTO.class)   );

        return response;
    }
    @Transactional
    public CareerDTO postCareer(CareerDTO careerDTO, long memberId) {
        CareerEntity careerEntity = getCareerDTO(careerDTO);

        careerEntity.setMemberId(memberProfileDAO.findById(memberId).orElseThrow());
        careerDAO.save(careerEntity);
        return modelMapper.map(careerEntity,CareerDTO.class);
    }
    @Transactional
    public CareerDTO putCareer(CareerDTO careerDTO, long memberId) {
        MemberProfileEntity member = memberProfileDAO.findById(memberId).orElseThrow();
        CareerEntity careerEntity = getCareerDTO(careerDTO);
        careerEntity.setMemberId(member);
        careerDAO.save(careerEntity);
        return modelMapper.map(careerEntity,CareerDTO.class);
    }

    @Transactional
    public List<ResponseSkill> putMemberSkill(long memberId, RequestSkillId skillId) {

        MemberSkillId memberSkillId = new MemberSkillId(memberId,skillId.getSkillId());
        MemberSkillEntity memberSkillEntity = new MemberSkillEntity(memberSkillId);
        memberSkillDAO.save(memberSkillEntity);
        List<MemberSkillEntity> memberSkills = memberSkillDAO.findByIdMemberId(memberId);
        List<ResponseSkill> returnValue = new ArrayList<>();
        memberSkills.forEach(
                skill ->{
                        ResponseSkill findSkill = skillService.getSkill(skill.getId().getSkillId());
                        returnValue.add(findSkill);
                }
        );
        return returnValue;
    }

    @Transactional
    public ResponseSkillList deleteMemberSkill(long memberId, RequestSkillId skillId) {
        MemberSkillId memberSkillId = new MemberSkillId(memberId,skillId.getSkillId());
        memberSkillDAO.deleteById(memberSkillId);
        List<MemberSkillEntity> memberSkills = memberSkillDAO.findByIdMemberId(memberId);
        ResponseSkillList returnValue = new ResponseSkillList();
        returnValue.setSkillList(new ArrayList<>());
        memberSkills.forEach(skill ->
                returnValue.getSkillList().add(modelMapper.map( skill.getId(),MemberSkillId.class))
        );
        return returnValue;
    }

    public CareerDTO deleteCareer(CareerDTO careerDTO, long memberId) {

        CareerEntity careerEntity = careerDAO.findById(careerDTO.getCareerId()).orElseThrow();
//        careerEntity.setMemberId(memberProfileDAO.findById(memberId).orElseThrow());
        careerDAO.delete(careerEntity);
        return modelMapper.map(careerEntity,CareerDTO.class);
    }

    public List<CareerDTO> getCareerList(long memberId) {
        MemberProfileEntity member = memberProfileDAO.findById(memberId).orElseThrow();
        List<CareerEntity> careerEntities = careerDAO.findByMemberId(member);
        List<CareerDTO> returnList = new ArrayList<>();
        careerEntities.forEach(careerEntity -> returnList.add(modelMapper.map(careerEntity,CareerDTO.class)));
        return returnList;
    }


    public List<ResponseRecruitCategory> getMemberRecruitCategoryList(long memberId){
        return client.getRecruitCategory(memberId);
    }

    @Transactional
    public List<ResponseRecruitCategory> putMemberRecruitCategory(long memberId, RequestRecruitCategory recruitId) {
        return client.postRecruitCategory(memberId,recruitId.getRecruitId());
    }

    @Transactional
    public List<ResponseRecruitCategory> deleteMemberRecruitCategory(long memberId, RequestRecruitCategory recruitId) {
        return client.deleteRecruitCategory(memberId,recruitId.getRecruitId());
    }

    public DegreeDTO getDegree(long memberId) {
        MemberProfileEntity memberProfile = memberProfileDAO.findById(memberId).orElseThrow();
        return modelMapper.map(memberProfile.getDegree(), DegreeDTO.class);
    }

    @Transactional
    public void putMemberSkills(long memberId, List<SkillEntity> skills) {
        memberSkillDAO.deleteAllByIdMemberId(memberId);
        skills.forEach(skill ->{
            RequestSkillId request = new RequestSkillId(skill.getSkillId());
            putMemberSkill(memberId,request);
        });
    }

}

