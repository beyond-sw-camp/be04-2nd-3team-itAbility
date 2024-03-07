package com.team3.itability.mypage.service;

import com.team3.itability.member.dao.MemberInfoRepo;
import com.team3.itability.member.dto.MemberInfoDTO;
import com.team3.itability.mypage.dao.*;
import com.team3.itability.mypage.dto.*;
import com.team3.itability.mypage.entity.*;
import com.team3.itability.mypage.entity.MemberRecruitCategoryId;
import com.team3.itability.mypage.enumData.IMG_USE;
import com.team3.itability.recruitment.aggregate.RecruitCategoryDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class MypageService {
    private final MemberProfileDAO memberProfileDAO;
    private final DegreeDAO degreeDAO;
    private final ImageDAO imageDAO;
    private final CareerDAO careerDAO;
    private final MemberSkillDAO memberSkillDAO;
    @Autowired
    private ResourceLoader resourceLoader;      //이미지용도
    private final SkillDAO skillDAO;
    private final MemberRecruitCategoryDAO memberRecruitCategoryDAO;
    private final RecruitCategoryDAO recruitCategoryDAO;

    private ModelMapper modelMapper;
    @Autowired
    private MemberInfoRepo memberInfoRepo;

    @Autowired
    public MypageService(MemberProfileDAO memberProfileDAO, DegreeDAO degreeDAO, ImageDAO imageDAO, CareerDAO careerDAO, MemberSkillDAO memberSkillDAO, SkillDAO skillDAO,
                         MemberRecruitCategoryDAO memberRecruitCategoryDAO,
                         RecruitCategoryDAO recruitCategoryDAO, ModelMapper modelMapper
                    ) {
        this.memberProfileDAO = memberProfileDAO;
        this.degreeDAO = degreeDAO;
        this.imageDAO = imageDAO;
        this.careerDAO = careerDAO;
        this.memberSkillDAO = memberSkillDAO;
        this.skillDAO = skillDAO;
        this.memberRecruitCategoryDAO = memberRecruitCategoryDAO;
        this.recruitCategoryDAO = recruitCategoryDAO;
        this.modelMapper = modelMapper;
    }



    /** <h1> 1. 자신의 마이페이지 정보 조회 - fin</h1> */
    @Transactional
    public MemberProfile printMypageData(long memberId){

        MemberProfileDTO memberProfileDTO =memberProfileDAO.findById(memberId).orElseThrow();
        if(memberProfileDTO.getImg()==null){
            ImageDTO image = new ImageDTO(memberProfileDTO.getMemberId()+"",IMG_USE.profile,"link","none" );
            image =  imageDAO.save(image);
            memberProfileDTO.setImg(image);
        }
        return modelMapper.map(memberProfileDTO,MemberProfile.class);
    }

    /** <h1>2. 마이페이지 수정</h1>*/
    @Transactional
    public void modifyMypage(long memberId, String nickname, String name, String phone, String birthdate) {
        MemberProfileDTO memberProfileDTO = memberProfileDAO.findById(memberId).orElseThrow();
        memberProfileDTO.setNickname(nickname);
        MemberInfoDTO memberInfoDTO = memberProfileDTO.getMemberInfo();
        memberInfoDTO.update(name,phone,birthdate);
        memberInfoRepo.save(memberInfoDTO);
    }
    /**
     * <h1>3. 학력 수정 - fin</h1>
     * */
    @Transactional
    public void modifyDegree(long memberId, Degree degree) {

        MemberProfileDTO memberProfileDTO = memberProfileDAO.findById(memberId).orElseThrow();
        DegreeDTO degreeDTO = memberProfileDTO.getDegree();
        degreeDTO.update(degree.getFinalEduName(),degree.getEnrollDate(),
                degree.getGraduateDate() ,degree.getMajor(), degree.isRegisterStatus() );
    }

    /**<h1>4. 경력 조회,수정,추가 - fin</h1>*/
    @Transactional(readOnly = true)
    public List<Career> printCareerList(long memberId){
        MemberProfileDTO memberProfileDTO = memberProfileDAO.findById(memberId).orElseThrow();
        List<CareerDTO> careerList =careerDAO.findByMemberId(memberProfileDTO);
        List<Career> returnValue= new ArrayList<>();
        careerList.forEach(careerEntity->{
            returnValue.add(new Career(careerEntity.getCareerId(),careerEntity.getCompanyName(),careerEntity.getStartDate()
            , careerEntity.getEndDate(),careerEntity.getRole(),careerEntity.getAssignedTask(), careerEntity.isCurrentJob()
            ,careerEntity.getMemberId())
            );
        });
        return returnValue;
    }

    @Transactional(readOnly = true)
    public Career printCareer(int careerId) {
        return modelMapper.map(careerDAO.findById(careerId).orElseThrow(), Career.class);
    }

    @Transactional
    public void modifyCareer(Career career) {
        CareerDTO careerDTO = getCareerDTO(career);
        careerDAO.save(careerDTO);
    }

    @Transactional
    public void addCareer(Career career, long memberId) {
        MemberProfileDTO member = memberProfileDAO.findById(memberId).orElseThrow();
        CareerDTO careerDTO = getCareerDTO(career);
        careerDTO.setMemberId(member);
        careerDAO.save(careerDTO);
    }

    private static CareerDTO getCareerDTO(Career career) {
        return new CareerDTO(career.getCareerId(), career.getCompanyName(), career.getStartDate(),
                career.getEndDate(), career.getRole(), career.getAssignedTask(), career.isCurrentJob(),
                career.getMemberId());
    }

    /**<h1>5.이미지 조회, 수정 - fin</h1>*/
    public Image getImage(long memberId) {
        MemberProfileDTO member = memberProfileDAO.findById(memberId).orElseThrow();
        return modelMapper.map(member.getImg(),Image.class);
    }

    @Transactional
    public void modifyImageDTO(long memberId, MultipartFile imgFile) throws IOException {
        MemberProfileDTO member = memberProfileDAO.findById(memberId).orElseThrow();
        ImageDTO image = member.getImg();
        Resource resource = resourceLoader.getResource("classpath:static/images/profile");
        String filePath =resource.getFile().getAbsolutePath();
        String originFileName= imgFile.getOriginalFilename();
        String ext = originFileName.substring(originFileName.lastIndexOf("."));
        new File(filePath+"/"+image.getImgId()+image.getExt()).delete();
        String saveName = memberId+ext;
        try {
            imgFile.transferTo(new File(filePath+"/"+saveName));
            image= new ImageDTO(member.getMemberId(),"/images/profile/"+saveName,IMG_USE.profile,ext);
            imageDAO.save(image);
        } catch(IOException e){
            new File(filePath+"/"+saveName).delete();
        }
    }



    /**<h1>6. 기술스택</h1>*/
    public MemberAndRemainSkill printMemberSkillList(Long memberId) {
        List<MemberSkillDTO> memberSkills = memberSkillDAO.findByIdMemberId(memberId);
        List<SkillDTO> memberSkillList = new ArrayList<>();
        List<SkillDTO> skills = skillDAO.findAll();
        Set<SkillDTO> remainSkillList = new HashSet<>(skills);
        memberSkills.forEach(memberSkill->{
            int skillId = memberSkill.getId().getSkillId();
            SkillDTO memberskillDTO =skillDAO.findById(skillId).orElseThrow();
            memberSkillList.add(memberskillDTO);
            remainSkillList.remove(memberskillDTO);
        });
        MemberAndRemainSkill returnValue = new MemberAndRemainSkill(memberId,memberSkillList,new ArrayList<>(remainSkillList));
        return returnValue;
    }

    public void removeMemberSkill(long memberId, int skillId) {
        MemberSkillId memberSkillId = new MemberSkillId(memberId,skillId);
        memberSkillDAO.deleteById(memberSkillId);
    }

    public void addMemberSkill(long memberId, int skillId) {
        MemberSkillId memberSkillId = new MemberSkillId(memberId,skillId);
        MemberSkillDTO memberSkillDTO = new MemberSkillDTO(memberSkillId);
        memberSkillDAO.save(memberSkillDTO);
    }

    public MemberAndRemainRecruitCategory printMemberRecruitList(Long memberId) {
        List<MemberRecruitCategoryDTO> memberSkills = memberRecruitCategoryDAO.findByIdMemberId(memberId);
        List<RecruitCategoryDTO> memberRecruitList = new ArrayList<>();
        // 없는 스킬 찾기
        List<RecruitCategoryDTO> skills = recruitCategoryDAO.findAll();
        Set<RecruitCategoryDTO> remainSkillList = new HashSet<>(skills);

        memberSkills.forEach(memberSkill->{
            int skillId = memberSkill.getId().getRecruitCategoryId();
            RecruitCategoryDTO memberskillDTO = recruitCategoryDAO.findById(skillId).orElseThrow();
            memberRecruitList.add(memberskillDTO);
            remainSkillList.remove(memberskillDTO);
        });
        memberSkills.forEach(System.out::println);
        MemberAndRemainRecruitCategory returnValue = new MemberAndRemainRecruitCategory(memberId,memberRecruitList,
                                                                            new ArrayList<>(remainSkillList));
        return returnValue;
    }


    public void removeMemberRecruitCagegory(long memberId, int recruitCategory) {
        MemberRecruitCategoryId memberRecruitCategoryId = new MemberRecruitCategoryId(memberId,recruitCategory);
        memberRecruitCategoryDAO.deleteById(memberRecruitCategoryId);
    }

    public void addMemberRecruitCagegory(long memberId, int recruitCategory) {
        MemberRecruitCategoryId memberRecruitCategoryId = new MemberRecruitCategoryId(memberId,recruitCategory);
        MemberRecruitCategoryDTO memberRecruitCategoryDTO = new MemberRecruitCategoryDTO(memberRecruitCategoryId);
        memberRecruitCategoryDAO.save(memberRecruitCategoryDTO);
    }
}
