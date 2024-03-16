package com.team3.memberservice.member;

import com.team3.memberservice.member.dto.MemberInfoDTO;
import com.team3.memberservice.member.dto.ResponseMember;
import com.team3.memberservice.member.service.MemberInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        System.out.println("memberId = " + memberId);
        return memberInfoService.findMember(memberId);
    }

    @GetMapping("/members")
    public List<ResponseMember> getAllMember(){
        System.out.println("페인 통해서 들어옴");
        return memberInfoService.findMemberList();
    }
    //[http://localhost:8000/member-service/member/2/report]
    @GetMapping("/member/{memberId}/report")
    public ResponseEntity<String> reportMember(@PathVariable Long memberId){
        System.out.println("들어온 memberId = " + memberId);
        MemberInfoDTO returnValue= memberInfoService.updateMemberReportCount(memberId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("ok");
    }
}
