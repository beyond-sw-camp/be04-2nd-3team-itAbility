package com.team3.itability.mypage.service;

import com.team3.itability.img.dao.ImageDAO;
import com.team3.itability.img.dto.ImageDTO;
import com.team3.itability.img.entity.ImageEntity;
import com.team3.itability.member.dao.MemberInfoRepo;
import com.team3.itability.member.dto.MemberInfoDTO;
import com.team3.itability.mypage.dao.*;
import com.team3.itability.mypage.dto.*;
import com.team3.itability.mypage.entity.*;
import com.team3.itability.mypage.entity.MemberRecruitCategoryId;
import com.team3.itability.img.enumData.IMG_USE;
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
    public MemberProfileDTO printMypage(long memberId){

        MemberProfileEntity memberProfileEntity =memberProfileDAO.findById(memberId).orElseThrow();
        if(memberProfileEntity.getImg()==null){
            ImageEntity image = new ImageEntity(memberProfileEntity.getMemberId()+"",IMG_USE.profile,"link","none" );
            image =  imageDAO.save(image);
            memberProfileEntity.setImg(image);
        }
        return modelMapper.map(memberProfileEntity, MemberProfileDTO.class);
    }

    /** <h1>2. 마이페이지 수정</h1>*/
    @Transactional
    public void modifyMypage(long memberId, String nickname, String name, String phone, String birthdate) {
        MemberProfileEntity memberProfileEntity = memberProfileDAO.findById(memberId).orElseThrow();
        memberProfileEntity.setNickname(nickname);
        MemberInfoDTO memberInfoDTO = memberProfileEntity.getMemberInfo();
        memberInfoDTO.update(name,phone,birthdate);
        memberInfoRepo.save(memberInfoDTO);
    }
    /**
     * <h1>3. 학력 수정 - fin</h1>
     * */
    @Transactional
    public void modifyDegree(long memberId, DegreeDTO degreeDTO) {

        MemberProfileEntity memberProfileEntity = memberProfileDAO.findById(memberId).orElseThrow();
        DegreeEntity degreeEntity = memberProfileEntity.getDegree();
        degreeEntity.update(degreeDTO.getFinalEduName(), degreeDTO.getEnrollDate(),
                degreeDTO.getGraduateDate() , degreeDTO.getMajor(), degreeDTO.isRegisterStatus() );
    }

    /**<h1>4. 경력 조회,수정,추가 - fin</h1>*/
    @Transactional(readOnly = true)
    public List<CareerDTO> printCareerList(long memberId){
        MemberProfileEntity memberProfileEntity = memberProfileDAO.findById(memberId).orElseThrow();
        List<CareerEntity> careerList =careerDAO.findByMemberId(memberProfileEntity);
        List<CareerDTO> returnValue= new ArrayList<>();
        careerList.forEach(careerEntity->{
            returnValue.add(new CareerDTO(careerEntity.getCareerId(),careerEntity.getCompanyName(),careerEntity.getStartDate()
            , careerEntity.getEndDate(),careerEntity.getRole(),careerEntity.getAssignedTask(), careerEntity.isCurrentJob()
            ,careerEntity.getMemberId())
            );
        });
        return returnValue;
    }
    @Transactional(readOnly = true)
    public CareerDTO printCareer(int careerId) {
        return modelMapper.map(careerDAO.findById(careerId).orElseThrow(), CareerDTO.class);
    }

    @Transactional
    public void modifyCareer(CareerDTO careerDTO) {
        CareerEntity careerEntity = getCareerDTO(careerDTO);
        careerDAO.save(careerEntity);
    }

    @Transactional
    public void addCareer(CareerDTO careerDTO, long memberId) {
        MemberProfileEntity member = memberProfileDAO.findById(memberId).orElseThrow();
        CareerEntity careerEntity = getCareerDTO(careerDTO);
        careerEntity.setMemberId(member);
        careerDAO.save(careerEntity);
    }


    private static CareerEntity getCareerDTO(CareerDTO careerDTO) {
        return new CareerEntity(careerDTO.getCareerId(),careerDTO.getCompanyName(), careerDTO.getStartDate(),
                careerDTO.getEndDate(), careerDTO.getRole(), careerDTO.getAssignedTask(), careerDTO.isCurrentJob(),
                careerDTO.getMemberId());
    }


    /**<h1>5.이미지 조회, 수정 - fin</h1>*/
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



    /**<h1>6. 기술스택</h1>*/
    public MemberAndRemainSkillDTO printMemberSkillList(Long memberId) {
        List<MemberSkillEntity> memberSkills = memberSkillDAO.findByIdMemberId(memberId);
        List<SkillEntity> memberSkillList = new ArrayList<>();
        List<SkillEntity> skills = skillDAO.findAll();
        Set<SkillEntity> remainSkillList = new HashSet<>(skills);
        memberSkills.forEach(memberSkill->{
            int skillId = memberSkill.getId().getSkillId();
            SkillEntity memberskillEntity =skillDAO.findById(skillId).orElseThrow();
            memberSkillList.add(memberskillEntity);
            remainSkillList.remove(memberskillEntity);
        });
        MemberAndRemainSkillDTO returnValue = new MemberAndRemainSkillDTO(memberId,memberSkillList,new ArrayList<>(remainSkillList));
        return returnValue;
    }

    public void removeMemberSkill(long memberId, int skillId) {
        MemberSkillId memberSkillId = new MemberSkillId(memberId,skillId);
        memberSkillDAO.deleteById(memberSkillId);
    }

    public void addMemberSkill(long memberId, int skillId) {
        MemberSkillId memberSkillId = new MemberSkillId(memberId,skillId);
        MemberSkillEntity memberSkillEntity = new MemberSkillEntity(memberSkillId);
        memberSkillDAO.save(memberSkillEntity);
    }

    public MemberAndRemainRecruitCategoryDTO printMemberRecruitList(Long memberId) {
        List<MemberRecruitCategoryEntity> memberSkills = memberRecruitCategoryDAO.findByIdMemberId(memberId);
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
        MemberAndRemainRecruitCategoryDTO returnValue = new MemberAndRemainRecruitCategoryDTO(memberId,memberRecruitList,
                                                                            new ArrayList<>(remainSkillList));
        return returnValue;
    }


    public void removeMemberRecruitCategory(long memberId, int recruitCategory) {
        MemberRecruitCategoryId memberRecruitCategoryId = new MemberRecruitCategoryId(memberId,recruitCategory);
        memberRecruitCategoryDAO.deleteById(memberRecruitCategoryId);
    }

    public void addMemberRecruitCategory(long memberId, int recruitCategory) {

        MemberRecruitCategoryId memberRecruitCategoryId = new MemberRecruitCategoryId(memberId,recruitCategory);
        MemberRecruitCategoryEntity memberRecruitCategoryEntity = new MemberRecruitCategoryEntity(memberRecruitCategoryId);
        memberRecruitCategoryDAO.save(memberRecruitCategoryEntity);
    }

    /**<h1>REST-API</h1>*/
    @Transactional
    public MemberProfileDTO PostMypage(RequestMember member, Long memberId) {
        MemberProfileEntity memberProfileEntity = memberProfileDAO.findById(memberId).orElseThrow();
        memberProfileEntity.setNickname(member.getNickname());
        MemberInfoDTO memberInfoDTO = memberProfileEntity.getMemberInfo();
        memberInfoDTO.update(member.getName(),member.getPhone(), member.getBirthDate());
        memberInfoRepo.save(memberInfoDTO);
        return modelMapper.map(memberProfileEntity,MemberProfileDTO.class);
    }

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
    public ResponseSkillList putMemberSkill(long memberId, RequestSkillId skillId) {

        MemberSkillId memberSkillId = new MemberSkillId(memberId,skillId.getSkillId());
        MemberSkillEntity memberSkillEntity = new MemberSkillEntity(memberSkillId);
        memberSkillDAO.save(memberSkillEntity);

        List<MemberSkillEntity> memberSkills = memberSkillDAO.findByIdMemberId(memberId);
        ResponseSkillList returnValue = new ResponseSkillList();
        returnValue.setSkillList(new ArrayList<>());
        memberSkills.forEach(skill -> returnValue.getSkillList().add(modelMapper.map( skill.getId(),MemberSkillId.class)));
        return returnValue;
    }
    @Transactional
    public ResponseSkillList deleteMemberSkill(long memberId, RequestSkillId skillId) {
        MemberSkillId memberSkillId = new MemberSkillId(memberId,skillId.getSkillId());
        memberSkillDAO.deleteById(memberSkillId);
        List<MemberSkillEntity> memberSkills = memberSkillDAO.findByIdMemberId(memberId);
        ResponseSkillList returnValue = new ResponseSkillList();
        returnValue.setSkillList(new ArrayList<>());
        memberSkills.forEach(skill -> returnValue.getSkillList().add(modelMapper.map( skill.getId(),MemberSkillId.class)));
        return returnValue;
    }

    @Transactional
    public List<MemberRecruitCategoryDTO> putMemberRecruitCategory(long memberId, RequestRecruitId recruitCategory) {

        MemberRecruitCategoryId memberRecruitCategoryId = new MemberRecruitCategoryId(memberId,recruitCategory.getRecruitId());
        MemberRecruitCategoryEntity memberRecruitCategoryEntity = new MemberRecruitCategoryEntity(memberRecruitCategoryId);
        memberRecruitCategoryDAO.save(memberRecruitCategoryEntity);
        List<MemberRecruitCategoryEntity> recruitList = memberRecruitCategoryDAO.findByIdMemberId(memberId);
        List<MemberRecruitCategoryDTO> returnValue = new ArrayList<>();
        recruitList.forEach(recruit-> returnValue.add(modelMapper.map(recruit,MemberRecruitCategoryDTO.class)));
        return returnValue;
    }

    @Transactional
    public List<MemberRecruitCategoryDTO> deleteMemberRecruitCategory(long memberId, RequestRecruitId recruitCategory) {
        MemberRecruitCategoryId memberRecruitCategoryId = new MemberRecruitCategoryId(memberId,recruitCategory.getRecruitId());
        memberRecruitCategoryDAO.deleteById(memberRecruitCategoryId);
        List<MemberRecruitCategoryEntity> recruitList = memberRecruitCategoryDAO.findByIdMemberId(memberId);
        List<MemberRecruitCategoryDTO> returnValue = new ArrayList<>();
        recruitList.forEach(recruit-> returnValue.add(modelMapper.map(recruit,MemberRecruitCategoryDTO.class)));
        return returnValue;
    }


    public CareerDTO deleteCareer(CareerDTO careerDTO, long memberId) {
        CareerEntity careerEntity = getCareerDTO(careerDTO);
        careerEntity.setMemberId(memberProfileDAO.findById(memberId).orElseThrow());
        careerDAO.delete(careerEntity);
        return modelMapper.map(careerEntity,CareerDTO.class);
    }
}
