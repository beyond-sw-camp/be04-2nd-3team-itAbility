package com.team3.itability.recruitment.controller;

import com.team3.itability.recruitment.dto.MemberRecruitsInfoDTO;
import com.team3.itability.recruitment.service.MemberRecruitsInfoService;
import com.team3.itability.recruitment.vo.MemberRecruitsInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/member_recruits")
public class MemberRecruitsInfoController {

    private final MemberRecruitsInfoService memberRecruitsInfoService;

    @Autowired
    public MemberRecruitsInfoController(MemberRecruitsInfoService memberRecruitsInfoService) {

        this.memberRecruitsInfoService = memberRecruitsInfoService;
    }

    // 모집글별 신청 회원 목록 조회
    @GetMapping("/list/{recruitId}")
    public ResponseEntity<Map<String, Object>> findMembersListByRecruitId(@PathVariable("recruitId") String recruitId) {

        List<MemberRecruitsInfoVO> memberList = memberRecruitsInfoService.findMembersListByRecruitId(recruitId);

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("members", memberList);

        return ResponseEntity.status(HttpStatus.OK).body(responseMap);
    }

    @PostMapping("/regist")
    public ResponseEntity<Map<String, Object>> registMemberRecruit(@RequestBody MemberRecruitsInfoVO memberRecruitsInfo) {

        memberRecruitsInfoService.registMemberRecruit(memberRecruitsInfo);

        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @PutMapping("/accept/{memberRecruitInfoId}")
    public ResponseEntity<?> acceptMemberRecruit(@PathVariable int memberRecruitInfoId) {

        memberRecruitsInfoService.acceptMemberRecruit(memberRecruitInfoId);

        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @PutMapping("/reject/{memberRecruitInfoId}")
    public ResponseEntity<?> rejectMemberRecruit(@PathVariable int memberRecruitInfoId) {

        memberRecruitsInfoService.rejectMemberRecruit(memberRecruitInfoId);

        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @DeleteMapping("/{memberRecruitInfoId}")
    public ResponseEntity<?> deleteMemberRecruit(@PathVariable int memberRecruitInfoId) {

        memberRecruitsInfoService.deleteMemberRecruit(memberRecruitInfoId);

        return ResponseEntity
                .noContent()
                .build();
    }

}
