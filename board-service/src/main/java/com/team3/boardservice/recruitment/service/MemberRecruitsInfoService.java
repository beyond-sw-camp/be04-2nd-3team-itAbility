package com.team3.boardservice.recruitment.service;

import com.team3.boardservice.client.MemberServerClient;
import com.team3.boardservice.member.dto.ResponseMember;
import com.team3.boardservice.recruitment.aggregate.MemberRecruitsInfoDTO;
import com.team3.boardservice.recruitment.aggregate.RecruitDTO;
import com.team3.boardservice.recruitment.aggregate.RecruitStatus;
import com.team3.boardservice.recruitment.repository.MemberRecruitsInfoRepo;
import com.team3.boardservice.recruitment.repository.MemberRecruitsMapper;
import com.team3.boardservice.recruitment.repository.RecruitRepo;
import com.team3.boardservice.recruitment.vo.MemberRecruitsInfoVO;
import com.team3.boardservice.recruitment.vo.ResponseMemberApplyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
public class MemberRecruitsInfoService {

    private final MemberRecruitsMapper memberRecruitsMapper;
    private final MemberRecruitsInfoRepo memberRecruitsInfoRepo;
    private final RecruitRepo recruitRepo;
    private final MemberServerClient memberServerClient;

    @Autowired
    public MemberRecruitsInfoService(MemberRecruitsMapper memberRecruitsMapper, MemberRecruitsInfoRepo memberRecruitsInfoRepo, RecruitRepo recruitRepo, MemberServerClient memberServerClient) {

        this.memberRecruitsMapper = memberRecruitsMapper;
        this.memberRecruitsInfoRepo = memberRecruitsInfoRepo;
        this.recruitRepo = recruitRepo;
        this.memberServerClient = memberServerClient;
    }

    @Transactional(readOnly = true)
    public List<MemberRecruitsInfoVO> findMembersListByRecruitId(String recruitId) {

        List<MemberRecruitsInfoVO> memberList = memberRecruitsMapper.selectMembersByRecruitId(recruitId);

        return memberList;
    }

    @Transactional
    public MemberRecruitsInfoDTO registMemberRecruit(MemberRecruitsInfoVO memberRecruitsInfoVO) {

        RecruitDTO recruitDTO = recruitRepo.getById(memberRecruitsInfoVO.getRecruitId());
//        MemberInfoDTO memberInfoDTO = memberInfoRepo.getById(memberRecruitsInfoVO.getMemberId());
        ResponseMember memberInfoDTO = memberServerClient.getMember(memberRecruitsInfoVO.getMemberId());

        MemberRecruitsInfoDTO memberRecruitsInfoDTO = new MemberRecruitsInfoDTO(memberRecruitsInfoVO.getMemberRecruitInfoId(), recruitDTO, memberRecruitsInfoVO.getRecruitStatus(), memberInfoDTO.getMemberId());

        memberRecruitsInfoRepo.save(memberRecruitsInfoDTO);

        return memberRecruitsInfoDTO;
    }

    @Transactional
    public MemberRecruitsInfoDTO acceptMemberRecruit(int memberRecruitInfoId) {

        MemberRecruitsInfoDTO foundRecruit = memberRecruitsInfoRepo.findById(memberRecruitInfoId).orElseThrow(IllegalAccessError::new);

        foundRecruit.setRecruitStatus(RecruitStatus.수락);

        return foundRecruit;
    }

    @Transactional
    public MemberRecruitsInfoDTO rejectMemberRecruit(int memberRecruitInfoId) {

        MemberRecruitsInfoDTO foundRecruit = memberRecruitsInfoRepo.findById(memberRecruitInfoId).orElseThrow(IllegalAccessError::new);

        foundRecruit.setRecruitStatus(RecruitStatus.거절);

        return foundRecruit;
    }

    @Transactional
    public void deleteMemberRecruit(int memberRecruitInfoId) {

        memberRecruitsInfoRepo.deleteById(memberRecruitInfoId);
    }

    public MemberRecruitsInfoDTO findMemberId(int recruitId) {

        MemberRecruitsInfoDTO memberRecruitsInfo = memberRecruitsInfoRepo.findById(recruitId).orElseThrow();

        return memberRecruitsInfo;
    }

    public List<MemberRecruitsInfoDTO> getMemberApplyList(long memberId) {
        List<MemberRecruitsInfoDTO> memberRecruitsInfoDTOS = memberRecruitsInfoRepo.findAllByMemberInfoDTO(memberId);
//        List<ResponseMemberApplyVO> returnValue = new ArrayList<>();
        System.out.println("memberRecruitsInfoDTOS = " + memberRecruitsInfoDTOS);

        return memberRecruitsInfoDTOS;
    }
}
