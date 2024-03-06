package com.team3.itability.member.service;

import com.team3.itability.member.dao.MemberInfoRepo;
import com.team3.itability.member.dto.MemberInfoDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberInfoService {

    private final ModelMapper mapper;
    private final MemberInfoRepo memberRepository;

    @Autowired
    public MemberInfoService(MemberInfoRepo memberRepository, ModelMapper mapper) {
        this.memberRepository = memberRepository;
        this.mapper = mapper;
    }

    public MemberInfoDTO findMemberInfoById(Long memberId) {
        return memberRepository.findById(memberId).orElse(null);
    }
}
