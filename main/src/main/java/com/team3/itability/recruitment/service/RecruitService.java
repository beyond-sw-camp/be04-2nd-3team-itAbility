package com.team3.itability.recruitment.service;

import com.team3.itability.member.dao.MemberInfoRepo;
import com.team3.itability.member.dto.MemberInfoDTO;
import com.team3.itability.mypage.dao.SkillDAO;
import com.team3.itability.mypage.entity.SkillEntity;
import com.team3.itability.recruitment.aggregate.*;
import com.team3.itability.recruitment.repository.*;
import com.team3.itability.recruitment.vo.RecruitVO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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
    @Transactional(readOnly = true)
    public List<RecruitCategoryDTO> findAllRecruitCategory() {

        List<RecruitCategoryDTO> recruitCategoryList = recruitCateRepo.findAll();

        return recruitCategoryList.stream().map(recruit -> mapper.map(recruit, RecruitCategoryDTO.class)).collect(Collectors.toList());
    }

    // 기술 카테고리 조회
    // mypage SkillDAO 사용
    @Transactional(readOnly = true)
    public List<SkillEntity> findAllSkill() {

        List<SkillEntity> skillList = skillRepo.findAll();

        return skillList.stream().map(recruit -> mapper.map(recruit, SkillEntity.class)).collect(Collectors.toList());
    }

    // 모집글 등록
    @Transactional
    public void registRecruit(RecruitDTO recruit, long memberId, int recruitCategoryId, int skillId) {

        MemberInfoDTO member = memberInfoRepo.findById(memberId).orElseThrow();
        recruit.setMemberInfoDTO(member);

        RecruitCategoryDTO recruitCategoryDTO = recruitCateRepo.findById(recruitCategoryId).orElseThrow();
        RefRecruitCategoryId refRecruitCategoryId = new RefRecruitCategoryId(recruit.getRecruitId(), recruitCategoryId);
        RefRecruitCategoryDTO refRecruitCategoryDTO = new RefRecruitCategoryDTO(refRecruitCategoryId, recruit, recruitCategoryDTO);

        SkillEntity skillEntity = skillRepo.findById(skillId).orElseThrow();
        RecruitSkillId recruitSkillId = new RecruitSkillId(recruit.getRecruitId(), skillId);
        RecruitSkillDTO recruitSkillDTO = new RecruitSkillDTO(recruitSkillId, recruit, skillEntity);

        recruitRepo.save(mapper.map(recruit, RecruitDTO.class));
        refRecruitRepo.save(refRecruitCategoryDTO);
        recruitSkillRepo.save(recruitSkillDTO);
    }

    // 모집글 수정
    @Transactional
    public void modifyRecruit(RecruitDTO recruit) {

        RecruitDTO foundRecruit = recruitRepo.findById(recruit.getRecruitId()).orElseThrow(IllegalAccessError::new);

        foundRecruit.setRecruitType(recruit.getRecruitType());
        foundRecruit.setRecruitTitle(recruit.getRecruitTitle());
        foundRecruit.setRecruitContent(recruit.getRecruitContent());
        foundRecruit.setRecruitExpDate(recruit.getRecruitExpDate());
        foundRecruit.setRecruitMbCnt(recruit.getRecruitMbCnt());
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

        return mapper.map(recruit, RecruitDTO.class);
    }

}
