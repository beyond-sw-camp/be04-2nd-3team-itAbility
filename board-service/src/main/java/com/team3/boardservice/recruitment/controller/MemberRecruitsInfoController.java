package com.team3.boardservice.recruitment.controller;

import com.team3.boardservice.recruitment.aggregate.MemberRecruitsInfoDTO;
import com.team3.boardservice.recruitment.aggregate.RecruitDTO;
import com.team3.boardservice.recruitment.service.MemberRecruitsInfoService;
import com.team3.boardservice.recruitment.vo.MemberRecruitsInfoVO;
import com.team3.boardservice.recruitment.vo.ResponseMemberApplyVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.team3.boardservice.recruitment.vo.ReqeuestRecruitCategory;
import com.team3.boardservice.recruitment.vo.ResponseRecruitCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/member_recruits")
@Tag(name = "모집글 신청", description = "모집글 신청 회원 조회, 신청, 신청 취소, 신청 수락, 신청 거절")
public class MemberRecruitsInfoController {

    private final MemberRecruitsInfoService memberRecruitsInfoService;

    @Autowired
    public MemberRecruitsInfoController(MemberRecruitsInfoService memberRecruitsInfoService) {

        this.memberRecruitsInfoService = memberRecruitsInfoService;
    }

    @GetMapping("/{recruitId}")
    public ResponseEntity<MemberRecruitsInfoDTO> findRecruitById(@PathVariable("recruitId") int recruitId) {

        MemberRecruitsInfoDTO memberRecruitInfo = memberRecruitsInfoService.findMemberId(recruitId);

        return ResponseEntity.ok().body(memberRecruitInfo);
    }

    // 모집글별 신청 회원 목록 조회
    @GetMapping("/list/{recruitId}")
    @Operation(summary = "모집글 신청 회원 조회", description = "사용자는 해당 모집글에 신청한 모든 회원 목록을 조회할 수 있습니다.")
    public ResponseEntity<List<MemberRecruitsInfoVO>> findMembersListByRecruitId(@PathVariable("recruitId") String recruitId) {

        List<MemberRecruitsInfoVO> memberList = memberRecruitsInfoService.findMembersListByRecruitId(recruitId);

        return ResponseEntity.ok().body(memberList);
    }

    @Transactional
    @PostMapping("/regist")
    @Operation(summary = "모집글 신청", description = "사용자는 입력한 내용으로 모집글에 신청할 수 있습니다.")
    public ResponseEntity<String> registMemberRecruit(@RequestBody MemberRecruitsInfoVO memberRecruitsInfo) {
        System.out.println(memberRecruitsInfo);
        MemberRecruitsInfoDTO memberRecruits = memberRecruitsInfoService.registMemberRecruit(memberRecruitsInfo);

        return ResponseEntity.ok("/localhost:5173/recruit");
//        /" + memberRecruitsInfo.getRecruitId()
    }

    @PutMapping("/accept/{memberRecruitInfoId}")
    @Operation(summary = "모집글 신청 수락", description = "해당 신청의 사용자 신청 상태가 수락으로 변경됩니다.")
    public ResponseEntity<MemberRecruitsInfoDTO> acceptMemberRecruit(@PathVariable int memberRecruitInfoId) {

        MemberRecruitsInfoDTO memberRecruit = memberRecruitsInfoService.acceptMemberRecruit(memberRecruitInfoId);

        return ResponseEntity.ok().body(memberRecruit);
    }

    @PutMapping("/reject/{memberRecruitInfoId}")
    @Operation(summary = "모집글 신청 거절", description = "해당 신청의 사용자 신청 상태가 거절로 변경됩니다.")
    public ResponseEntity<MemberRecruitsInfoDTO> rejectMemberRecruit(@PathVariable int memberRecruitInfoId) {

        MemberRecruitsInfoDTO memberRecruit = memberRecruitsInfoService.rejectMemberRecruit(memberRecruitInfoId);

        return ResponseEntity.ok().body(memberRecruit);
    }

    @DeleteMapping("/{memberRecruitInfoId}")
    @Operation(summary = "모집글 신청 취소", description = "사용자는 해당 신청을 취소할 수 있습니다.")
    public ResponseEntity<?> deleteMemberRecruit(@PathVariable int memberRecruitInfoId) {

        memberRecruitsInfoService.deleteMemberRecruit(memberRecruitInfoId);

        return ResponseEntity
                .noContent()
                .build();
    }

    @GetMapping("/member-apply-list/{memberId}")
    @Operation(summary = "자신이 신청한 모집글 조회", description = "사용자는 자신이 신청한 모집글 목록들을 조회할 수 있습니다.")
    public ResponseEntity<List<MemberRecruitsInfoDTO> > getMemberApplyList(@PathVariable long memberId){
        List<MemberRecruitsInfoDTO> returnValue= memberRecruitsInfoService.getMemberApplyList(memberId);
        return ResponseEntity.ok().body(returnValue);
    }

}
