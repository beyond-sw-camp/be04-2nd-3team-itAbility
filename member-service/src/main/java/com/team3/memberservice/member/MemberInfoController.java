package com.team3.memberservice.member;

import com.team3.memberservice.member.dto.MemberInfoDTO;
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
        return memberInfoService.findMember(memberId);
    }

    @GetMapping("/members")
    public List<MemberInfoDTO> getAllMember(){
        return memberInfoService.findMemberList();
    }
    //[http://localhost:8000/member-service/member/2/report]
    @PutMapping("/member/{memberId}/report")
    public ResponseEntity<MemberInfoDTO> reportMember(@PathVariable Long memberId){
        System.out.println("들어온 memberId = " + memberId);
        MemberInfoDTO returnValue= memberInfoService.updateMemberReportCount(memberId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(returnValue);
    }
}
