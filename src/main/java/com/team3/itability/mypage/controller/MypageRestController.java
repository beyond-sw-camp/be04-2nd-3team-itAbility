package com.team3.itability.mypage.controller;

import com.team3.itability.mypage.dto.*;
import com.team3.itability.mypage.service.MypageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/mypage")
public class MypageRestController {
    private final MypageService mypageService;
    @Autowired
    public MypageRestController(MypageService mypageService) {
        this.mypageService = mypageService;
    }

    /**
     * <h1>MyPage</h1>
     * 마이페이지 컨트롤러
     * */
    // 1. 마이페이지 조회  - fin
    @GetMapping("/{memberId}")
    public ResponseEntity<MypageDTO> getMypage(@PathVariable long memberId){
        MemberProfileDTO profile = mypageService.printMypage(memberId);
        List<CareerDTO> careerDTOList = mypageService.printCareerList(memberId);
        MemberAndRemainSkillDTO skillDTOS = mypageService.printMemberSkillList(memberId);
        MemberAndRemainRecruitCategoryDTO recruitCategoryDTOS = mypageService.printMemberRecruitList(memberId);
        MypageDTO mypageDTO = new MypageDTO(profile,careerDTOList,skillDTOS,recruitCategoryDTOS);

        return ResponseEntity.status(HttpStatus.CREATED).body(mypageDTO);
    }
    // 2. 회원 정보 수정(profile, info) - fin
    @PostMapping("/{memberId}")
    public ResponseEntity<MemberProfileDTO> postMypage(@PathVariable long memberId, @RequestBody RequestMember member) {
        MemberProfileDTO returnValue = mypageService.PostMypage(member,memberId);
        return ResponseEntity.status(HttpStatus.CREATED).body(returnValue);
    }
    //3. 학력 수정 - fin
    @PostMapping("/{memberId}/degree")
    public ResponseEntity<ResponseDegree> postDegree(@PathVariable long memberId, @RequestBody DegreeDTO degreeDTO){
        ResponseDegree returnValue = mypageService.postDegree(memberId, degreeDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(returnValue);
    }
    //4. 경력 수정-fin
    @PostMapping("/{memberId}/career")
    public ResponseEntity<CareerDTO> postCareer(@PathVariable long memberId,@RequestBody CareerDTO careerDTO){
        CareerDTO returnValue =  mypageService.postCareer(careerDTO, memberId);
        return ResponseEntity.status(HttpStatus.CREATED).body(returnValue);
    }

    //5. 경력 추가 - fin
    @PutMapping("/{memberId}/career")
    public ResponseEntity<CareerDTO> putCareer(@PathVariable long memberId, @RequestBody CareerDTO careerDTO){
        CareerDTO returnValue = mypageService.putCareer(careerDTO,memberId);
        return ResponseEntity.status(HttpStatus.CREATED).body(returnValue);
    }
    //5-1. 경력 제거
    @DeleteMapping("/{memberId}/career")
    public ResponseEntity<CareerDTO> deleteCareer(@PathVariable long memberId, @RequestBody CareerDTO careerDTO){
        System.out.println("careerDTO = " + careerDTO);
        CareerDTO returnValue = mypageService.deleteCareer(careerDTO,memberId);
        System.out.println("삭제 완료");
        return ResponseEntity.status(HttpStatus.OK).body(returnValue);
    }

    //6. 이미지 수정 - html로 소개

    //7. 맴버 스킬 추가
    @PutMapping("/{memberId}/skill")
    public ResponseEntity<ResponseSkillList> putMemberSkill(@PathVariable long memberId, @RequestBody RequestSkillId skillId){
        ResponseSkillList returnValue = mypageService.putMemberSkill(memberId, skillId);
        return ResponseEntity.status(HttpStatus.CREATED).body(returnValue);
    }
    //8. 맴버 스킬 삭제
    @DeleteMapping("/{memberId}/skill")
    public ResponseEntity<ResponseSkillList> deleteMemberSkill(@PathVariable long memberId, @RequestBody RequestSkillId skillId){
        ResponseSkillList returnValue = mypageService.deleteMemberSkill(memberId, skillId);
        return ResponseEntity.status(HttpStatus.CREATED).body(returnValue);
    }
    //9. 맴버 분야 추가
    @PutMapping("/{memberId}/recruit")
    public ResponseEntity<List<MemberRecruitCategoryDTO>> putMemberRecruit(@PathVariable long memberId, @RequestBody RequestRecruitId recruitId ){
        List<MemberRecruitCategoryDTO> returnValue = mypageService.putMemberRecruitCategory(memberId, recruitId);
        return ResponseEntity.status(HttpStatus.CREATED).body(returnValue);
    }
    //10. 맴버 분야 삭제
    @DeleteMapping("/{memberId}/recruit")
    public ResponseEntity<List<MemberRecruitCategoryDTO>> deleteMemberRecruit(@PathVariable long memberId, @RequestBody RequestRecruitId recruitCategory){
        List<MemberRecruitCategoryDTO> returnValue = mypageService.deleteMemberRecruitCategory(memberId, recruitCategory);
        return ResponseEntity.status(HttpStatus.CREATED).body(returnValue);
    }
}
