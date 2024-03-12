package com.team3.memberservice.member;

import com.team3.memberservice.member.dto.MemberInfoDTO;
import com.team3.memberservice.member.service.MemberInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/info")
public class MemberInfoController {
    private final MemberInfoService memberInfoService;

    @Autowired
    public MemberInfoController(MemberInfoService memberInfoService) {
        this.memberInfoService = memberInfoService;
    }

    @GetMapping("/member/{memberId}")
    public MemberInfoDTO getMember(@PathVariable long memberId){
        return memberInfoService.findMember(memberId);
    }

    @GetMapping("/members")
    public List<MemberInfoDTO> getAllMember(){
        return memberInfoService.findMemberList();
    }

}
