package com.team3.memberservice.member.service;

import com.team3.memberservice.member.dao.MemberInfoRepo;
import com.team3.memberservice.member.dto.MemberInfoDTO;
import com.team3.memberservice.member.dto.ResponseMember;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class MemberInfoService {

    private final ModelMapper mapper;
    private final MemberInfoRepo memberRepository;

    @Autowired
    public MemberInfoService(MemberInfoRepo memberRepository, ModelMapper mapper) {
        this.memberRepository = memberRepository;
        this.mapper = mapper;
    }

    /**
     * <h3>findMemberInfoById:</h3>
     * <h3>findMember:</h3>
     * <h3>findMemberList:</h3>
     * <h3>updateMemberReportCount:</h3>
     * */
    public MemberInfoDTO findMemberInfoById(Long memberId) {
        return memberRepository.findById(memberId).orElse(null);
    }

    public MemberInfoDTO findMember(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new NoSuchElementException("No member found with ID: " + memberId));
    }

    public List<ResponseMember> findMemberList(){
        List<ResponseMember> memberList= new ArrayList<>();
        List<MemberInfoDTO> memberInfoDTOS= memberRepository.findAll();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        memberInfoDTOS.forEach(memberInfoDTO -> {
            System.out.println("memberInfoDTO = " + memberInfoDTO);
            memberList.add(new ResponseMember(memberInfoDTO));
        });
        return memberList;
    }

    public MemberInfoDTO updateMemberReportCount(Long memberId) {
        System.out.println(memberId);
        MemberInfoDTO member = memberRepository.findById(memberId)
                .orElseThrow(() -> new EntityNotFoundException("멤버를 찾을 수 없습니다."));
        member.setMbReportCount(member.getMbReportCount() + 1);
        memberRepository.save(member);
        return member;
    }

}
