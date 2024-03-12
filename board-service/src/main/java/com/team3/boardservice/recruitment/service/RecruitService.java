package com.team3.boardservice.recruitment.service;

import com.team3.boardservice.recruitment.aggregate.*;
import com.team3.boardservice.recruitment.repository.*;
import com.team3.itability.recruitment.vo.RecruitVO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RecruitService {

    private final ModelMapper mapper;
    private final RecruitMapper recruitMapper;
    private final RecruitRepo recruitRepo;
    private final RecruitCateRepo recruitCateRepo;
    private final SkillDAO skillRepo;
    private final MemberInfoRepo memberInfoRepo;
    private final RefRecruitRepo refRecruitRepo;
    private final RecruitSkillRepo recruitSkillRepo;

    @Autowired
    public RecruitService(
                            ModelMapper mapper,
                            RecruitMapper recruitMapper,
                            RecruitRepo recruitRepo,
                            MemberInfoRepo memberInfoRepo,
                            RecruitCateRepo recruitCateRepo,
                            RefRecruitRepo refRecruitRepo,
                            SkillDAO skillRepo,
                            RecruitSkillRepo recruitSkillRepo)
    {
        this.mapper = mapper;
        this.recruitMapper = recruitMapper;
        this.recruitRepo = recruitRepo;
        this.memberInfoRepo = memberInfoRepo;
        this.recruitCateRepo = recruitCateRepo;
        this.refRecruitRepo = refRecruitRepo;
        this.skillRepo = skillRepo;
        this.recruitSkillRepo = recruitSkillRepo;
    }

    // 모집군 카테고리 조회
//    @Transactional(readOnly = true)
//    public List<RecruitCategoryDTO> findAllRecruitCategory() {
//
//        List<RecruitCategoryDTO> recruitCategoryList = recruitCateRepo.findAll();
//
//        return recruitCategoryList;
//    }

    // 기술 카테고리 조회
    // mypage SkillDAO 사용
//    @Transactional(readOnly = true)
//    public List<SkillEntity> findAllSkill() {
//
//        List<SkillEntity> skillList = skillRepo.findAll();
//
//        return skillList;
//    }

    // 모집글 등록
    @Transactional
    public RecruitDTO registRecruit(RecruitVO recruit) {

        MemberInfoDTO member = memberInfoRepo.findById(recruit.getMemberId()).orElseThrow();
        RecruitDTO recruitDTO = new RecruitDTO(recruit.getRecruitType(), recruit.getRecruitTitle(), recruit.getRecruitContent(), recruit.getRecruitExpDate(), recruit.getRecruitMbCnt(), member);

        RecruitCategoryDTO recruitCategoryDTO = recruitCateRepo.findById(recruit.getRecruitCategoryId()).orElseThrow();
        RefRecruitCategoryId refRecruitCategoryId = new RefRecruitCategoryId(recruit.getRecruitId(), recruit.getRecruitCategoryId());
        RefRecruitCategoryDTO refRecruitCategoryDTO = new RefRecruitCategoryDTO(refRecruitCategoryId, recruitDTO, recruitCategoryDTO);

        SkillEntity skillEntity = skillRepo.findById(recruit.getSkillId()).orElseThrow();
        RecruitSkillId recruitSkillId = new RecruitSkillId(recruit.getRecruitId(), recruit.getSkillId());
        RecruitSkillDTO recruitSkillDTO = new RecruitSkillDTO(recruitSkillId, recruitDTO, skillEntity);

        recruitRepo.save(recruitDTO);
        refRecruitRepo.save(refRecruitCategoryDTO);
        recruitSkillRepo.save(recruitSkillDTO);

        return recruitDTO;
    }

    // 모집글 수정
    @Transactional
    public RecruitDTO modifyRecruit(int recruitId, RecruitVO recruit) {

        RecruitDTO foundRecruit = recruitRepo.findById(recruitId).orElseThrow(IllegalAccessError::new);

        RefRecruitCategoryId refRecruitCategoryId = new RefRecruitCategoryId(recruitId, recruit.getRecruitCategoryId());
        RefRecruitCategoryDTO foundRefRecruit = refRecruitRepo.findById(refRecruitCategoryId).orElseThrow(IllegalAccessError::new);

        RecruitSkillId recruitSkillId = new RecruitSkillId(recruitId, recruit.getSkillId());
        RecruitSkillDTO foundRecruitSkill = recruitSkillRepo.findById(recruitSkillId).orElseThrow(IllegalAccessError::new);

        foundRecruit.setRecruitType(recruit.getRecruitType());
        foundRecruit.setRecruitTitle(recruit.getRecruitTitle());
        foundRecruit.setRecruitContent(recruit.getRecruitContent());
        foundRecruit.setRecruitExpDate(recruit.getRecruitExpDate());
        foundRecruit.setRecruitMbCnt(recruit.getRecruitMbCnt());

        foundRefRecruit.setRecruitCategory(recruitCateRepo.findById(recruit.getRecruitCategoryId()).orElseThrow());
        foundRecruitSkill.setSkillEntity(skillRepo.findById(recruit.getSkillId()).orElseThrow());

        System.out.println("foundRecruit = " + foundRecruit);
        return foundRecruit;
    }

    // 모집글 삭제
    @Transactional
    public void deleteRecruit(int recruitId) {
        recruitRepo.deleteById(recruitId);
    }

    // 모집글 목록
    @Transactional(readOnly = true)
    public List<RecruitVO> findRecruitList() {

        List<RecruitVO> recruitList = recruitMapper.findRecruitList();

        return recruitList;
    }

    // 모집글 필터

    // 모집글 상세 페이지
    @Transactional(readOnly = true)
    public RecruitDTO findRecruitById(int recruitId) {

        RecruitDTO recruit = recruitRepo.findById(recruitId).orElseThrow(IllegalArgumentException::new);

        return recruit;
    }

}
