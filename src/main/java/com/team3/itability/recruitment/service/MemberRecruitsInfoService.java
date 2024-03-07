package com.team3.itability.recruitment.service;

import com.team3.itability.member.dao.MemberInfoRepo;
import com.team3.itability.member.dto.MemberInfoDTO;
import com.team3.itability.recruitment.dto.MemberRecruitsInfoDTO;
import com.team3.itability.recruitment.dto.RecruitDTO;
import com.team3.itability.recruitment.dto.RecruitStatus;
import com.team3.itability.recruitment.repository.MemberRecruitsInfoRepo;
import com.team3.itability.recruitment.repository.MemberRecruitsMapper;
import com.team3.itability.recruitment.repository.RecruitRepo;
import com.team3.itability.recruitment.vo.MemberRecruitsInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberRecruitsInfoService {

    private final MemberRecruitsMapper memberRecruitsMapper;
    private final MemberRecruitsInfoRepo memberRecruitsInfoRepo;
    private final RecruitRepo recruitRepo;
    private final MemberInfoRepo memberInfoRepo;

    @Autowired
    public MemberRecruitsInfoService(MemberRecruitsMapper memberRecruitsMapper, MemberRecruitsInfoRepo memberRecruitsInfoRepo, RecruitRepo recruitRepo, MemberInfoRepo memberInfoRepo) {
        this.memberRecruitsMapper = memberRecruitsMapper;
        this.memberRecruitsInfoRepo = memberRecruitsInfoRepo;
        this.recruitRepo = recruitRepo;
        this.memberInfoRepo = memberInfoRepo;
    }

    public List<MemberRecruitsInfoVO> findMembersListByRecruitId(String recruitId) {
        List<MemberRecruitsInfoVO> memberList = memberRecruitsMapper.selectMembersByRecruitId(recruitId);
        return memberList;
    }

    public void registMemberRecruit(MemberRecruitsInfoVO memberRecruitsInfoVO) {

        RecruitDTO recruitDTO = recruitRepo.getById(memberRecruitsInfoVO.getRecruitId());
        MemberInfoDTO memberInfoDTO = memberInfoRepo.getById(memberRecruitsInfoVO.getMemberId());

        MemberRecruitsInfoDTO memberRecruitsInfoDTO = new MemberRecruitsInfoDTO(memberRecruitsInfoVO.getMemberRecruitInfoId(), recruitDTO, memberRecruitsInfoVO.getRecruitStatus(), memberInfoDTO);

        memberRecruitsInfoRepo.save(memberRecruitsInfoDTO);
    }


    public void acceptMemberRecruit(int memberRecruitInfoId) {

        MemberRecruitsInfoDTO foundRecruit = memberRecruitsInfoRepo.findById(memberRecruitInfoId).orElseThrow(IllegalAccessError::new);

        foundRecruit.setRecruitStatus(RecruitStatus.수락);
        memberRecruitsInfoRepo.save(foundRecruit);
    }

    public void rejectMemberRecruit(int memberRecruitInfoId) {

        MemberRecruitsInfoDTO foundRecruit = memberRecruitsInfoRepo.findById(memberRecruitInfoId).orElseThrow(IllegalAccessError::new);

        foundRecruit.setRecruitStatus(RecruitStatus.거절);
        memberRecruitsInfoRepo.save(foundRecruit);
    }

    public void deleteMemberRecruit(int memberRecruitInfoId) {

        memberRecruitsInfoRepo.deleteById(memberRecruitInfoId);
    }

}
