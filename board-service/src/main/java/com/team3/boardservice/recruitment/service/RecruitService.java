package com.team3.boardservice.recruitment.service;

import com.team3.boardservice.client.MemberServerClient;
import com.team3.boardservice.member.dto.ResponseMember;

import com.team3.boardservice.mypage.dao.MemberRecruitCategoryDAO;
import com.team3.boardservice.mypage.entity.MemberRecruitCategoryEntity;
import com.team3.boardservice.mypage.entity.MemberRecruitCategoryId;
import com.team3.boardservice.recruitment.aggregate.*;
import com.team3.boardservice.recruitment.repository.*;
import com.team3.boardservice.recruitment.vo.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecruitService {

    private final ModelMapper mapper;
    private final RecruitMapper recruitMapper;
    private final RecruitRepo recruitRepo;
    private final RecruitCateRepo recruitCateRepo;
    private final RefRecruitRepo refRecruitRepo;
    private final RecruitSkillRepo recruitSkillRepo;
    private final MemberServerClient memberServerClient;
    private final MemberRecruitCategoryDAO memberRecruitCategoryDAO;
    private final MemberRecruitsInfoRepo memberRecruitsInfoRepo;


    @Autowired
    public RecruitService(
                            ModelMapper mapper,
                            RecruitMapper recruitMapper,
                            RecruitRepo recruitRepo,
                            RecruitCateRepo recruitCateRepo,
                            RefRecruitRepo refRecruitRepo,
                            RecruitSkillRepo recruitSkillRepo,
                            MemberServerClient memberServerClient,
                            MemberRecruitCategoryDAO memberRecruitCategoryDAO,
                            MemberRecruitsInfoRepo memberRecruitsInfoRepo)
    {
        this.mapper = mapper;
        this.recruitMapper = recruitMapper;
        this.recruitRepo = recruitRepo;
        this.recruitCateRepo = recruitCateRepo;
        this.refRecruitRepo = refRecruitRepo;
        this.recruitSkillRepo = recruitSkillRepo;
        this.memberServerClient = memberServerClient;
        this.memberRecruitCategoryDAO = memberRecruitCategoryDAO;
        this.memberRecruitsInfoRepo = memberRecruitsInfoRepo;
    }

    // 모집군 카테고리 조회
    @Transactional(readOnly = true)
    public List<RecruitCategoryDTO> findAllRecruitCategory() {

        List<RecruitCategoryDTO> recruitCategoryList = recruitCateRepo.findAll();

        return recruitCategoryList;
    }

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

//        MemberInfoDTO member = memberInfoRepo.findById(recruit.getMemberId()).orElseThrow();
//        ResponseMember member = memberServerClient.getMember(recruit.getMemberId());
        RecruitDTO recruitDTO = new RecruitDTO(recruit.getRecruitType(), recruit.getRecruitTitle(), recruit.getRecruitContent(), recruit.getRecruitExpDate(), recruit.getRecruitMbCnt(), recruit.getMemberId());

//        for (int categoryId: recruit.getRecruitCategoryId()){
//            RecruitCategoryDTO recruitCategoryDTO = recruitCateRepo.findById(categoryId).orElseThrow();
//            RefRecruitCategoryId refRecruitCategoryId = new RefRecruitCategoryId(recruit.getRecruitId(), categoryId);
//            RefRecruitCategoryDTO refRecruitCategoryDTO = new RefRecruitCategoryDTO(refRecruitCategoryId, recruitDTO, recruitCategoryDTO);
//
//            refRecruitRepo.save(refRecruitCategoryDTO);
//        }
//
//        for (int skillId: recruit.getSkillId()) {
//            ResponseSkill skillEntity = memberServerClient.getSkill(skillId);
//            RecruitSkillId recruitSkillId = new RecruitSkillId(recruit.getRecruitId(), skillId);
//            RecruitSkillDTO recruitSkillDTO = new RecruitSkillDTO(recruitSkillId, recruitDTO, skillEntity.getSkillId());
//
//            recruitSkillRepo.save(recruitSkillDTO);
//        }

        recruitRepo.save(recruitDTO);

        return recruitDTO;
    }

    // 모집글 수정
    @Transactional
    public RecruitDTO modifyRecruit(int recruitId, RecruitVO recruit) {

        RecruitDTO foundRecruit = recruitRepo.findById(recruitId).orElseThrow(IllegalAccessError::new);

        foundRecruit.setRecruitType(recruit.getRecruitType());
        foundRecruit.setRecruitTitle(recruit.getRecruitTitle());
        foundRecruit.setRecruitContent(recruit.getRecruitContent());
        foundRecruit.setRecruitExpDate(recruit.getRecruitExpDate());
        foundRecruit.setRecruitMbCnt(recruit.getRecruitMbCnt());

        System.out.println("foundRecruit = " + foundRecruit);
        return foundRecruit;
    }

    // 모집글 삭제
    @Transactional
    public void deleteRecruit(int recruitId) {
//        memberRecruitsInfoRepo.deleteAllByRecruitDTORecruitId(recruitId);
//        refRecruitRepo.deleteAllbyRecruitRecruitId(recruitId);
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

    public void test(long memberId) {
        ResponseMember memberInfoDTO = memberServerClient.getMember(memberId);
        System.out.println("memberInfoDTO = " + memberInfoDTO);
    }


    public List<ResponseRecruitCategory> getMemberRecruitCategory(long memberId) {
        List<MemberRecruitCategoryEntity> memberRecruitCategory = memberRecruitCategoryDAO.findByIdMemberId(memberId);
        List<ResponseRecruitCategory> responseList = new ArrayList<>();
        System.out.println("memberRecruitCategory = " + memberRecruitCategory);
        memberRecruitCategory.forEach( category->{
            int categoryId = category.getId().getRecruitCategoryId();
            System.out.println("categoryId = " + categoryId);
            RecruitCategoryDTO recruitCategoryDTO = recruitCateRepo.findById(categoryId).orElseThrow();
            responseList.add(mapper.map(recruitCategoryDTO,ResponseRecruitCategory.class));
        });
        return responseList;
    }

    public List<ResponseRecruitCategory> postMemberRecruitCategery(long memberId, int recruitId) {
        MemberRecruitCategoryEntity entity = new MemberRecruitCategoryEntity(new MemberRecruitCategoryId(memberId,recruitId));
        memberRecruitCategoryDAO.save(entity);

        return getMemberRecruitCategory(memberId);
    }

    public List<ResponseRecruitCategory> deleteMemberRecruitCategery(long memberId, int recruitId) {
        MemberRecruitCategoryEntity entity = memberRecruitCategoryDAO.findById(new MemberRecruitCategoryId(memberId,recruitId)).orElseThrow();
        memberRecruitCategoryDAO.delete(entity);
        return getMemberRecruitCategory(memberId);
    }

    public void putAllMemberRecruitCategery(long memberId, List<RequestRecruitCategory> recruits) {

        memberRecruitCategoryDAO.deleteAllByIdMemberId(memberId);

        recruits.forEach(recruit ->{
            postMemberRecruitCategery(memberId,recruit.getRecruitCategoryId());
        });
    }

    public List<RecruitDTO> getMemberRecruitList(long memberId) {
        List<RecruitDTO> recruitDTOS = recruitRepo.findByMemberInfoDTO(memberId);
        return recruitDTOS;
    }

    public List<RefRecruitCategoryVO> findRecruitCategory(int recruitId) {

        List<RefRecruitCategoryDTO> recruitCategoryId = refRecruitRepo.findAllByIdRecruitId(recruitId);
        List<RefRecruitCategoryVO> refCateVOList = new ArrayList<>();

        for (RefRecruitCategoryDTO refRecruitCategoryDTO : recruitCategoryId) {
            int refCateId = refRecruitCategoryDTO.getId().getRecruitCategoryId();

            refCateVOList.add(new RefRecruitCategoryVO(recruitId, refCateId, recruitCateRepo.findById(refCateId).get().getRecruitName()));
        }

        return refCateVOList;
    }

    public List<RecruitSkillVO> findRecruitSkill(int recruitId) {

        List<RecruitSkillDTO> recruitSkillId = recruitSkillRepo.findAllByIdRecruitId(recruitId);
        List<RecruitSkillVO> recruitSkillVOList = new ArrayList<>();

        for (RecruitSkillDTO recruitSkillDTO : recruitSkillId) {
            int skillId = recruitSkillDTO.getId().getSkillId();

            recruitSkillVOList.add(new RecruitSkillVO(recruitId, skillId, memberServerClient.getSkill(skillId).getSkillName()));
        }

        return recruitSkillVOList;
    }

    public List<RecruitCategoryDTO> getAllRecruitCategory() {
        return recruitCateRepo.findAll();
    }
}
