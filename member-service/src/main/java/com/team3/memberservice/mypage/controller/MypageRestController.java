package com.team3.memberservice.mypage.controller;

import com.team3.memberservice.mypage.dto.*;
import com.team3.memberservice.mypage.service.MypageService;
import com.team3.memberservice.mypage.service.SkillService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/mypage")
@Tag(name = "마이페이지", description = "입니다.")
public class MypageRestController {
    private final MypageService mypageService;
    private final SkillService skillService;
    @Autowired
    public MypageRestController(MypageService mypageService, SkillService skillService) {
        this.mypageService = mypageService;
        this.skillService = skillService;
    }

    /**
     * <h1>MyPage</h1>
     * 마이페이지 컨트롤러
     * */
    // 1. 마이페이지 조회  - fin
    @GetMapping("/{memberId}")
    public ResponseEntity<MypageDTO> getMypage(@PathVariable long memberId){
        MemberProfileDTO profile = mypageService.getMypage(memberId);
        List<CareerDTO> careerDTOList = mypageService.getCareerList(memberId);
        List<ResponseSkill> skillDTOS = mypageService.getMemberSkill(memberId);

//        MemberAndRemainRecruitCategoryDTO recruitCategoryDTOS = mypageService.printMemberRecruitList(memberId);
//        MypageDTO mypageDTO = new MypageDTO(profile,careerDTOList,skillDTOS,recruitCategoryDTOS);
        MypageDTO mypageDTO = new MypageDTO(profile,careerDTOList,skillDTOS);
        return ResponseEntity.status(HttpStatus.CREATED).body(mypageDTO);
    }
    // 2. 회원 정보 수정(profile, info) - fin
    @PostMapping("/{memberId}")
    public ResponseEntity<MemberProfileDTO> postMypage(@PathVariable long memberId, @RequestBody RequestMember member) {
        MemberProfileDTO returnValue = mypageService.PostMypage(member,memberId);
        return ResponseEntity.status(HttpStatus.CREATED).body(returnValue);
    }
    //3. 학력 수정 - fin
    @Operation(summary = "학력 수정", description = "설명란")
    @PostMapping("/{memberId}/degree")
    public ResponseEntity<ResponseDegree> postDegree(@PathVariable@Parameter(example = "6249388071526484416") long memberId, @RequestBody DegreeDTO degreeDTO){
        ResponseDegree returnValue = mypageService.postDegree(memberId, degreeDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(returnValue);
    }
    // 경력 조회
    @GetMapping("/{memberId}/careers")
    public ResponseEntity<List<CareerDTO>> getCareerList(@PathVariable long memberId){
        List<CareerDTO> returnValue = mypageService.getCareerList(memberId);
        return ResponseEntity.status(HttpStatus.OK).body(returnValue);
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
        CareerDTO returnValue = mypageService.deleteCareer(careerDTO,memberId);
        return ResponseEntity.status(HttpStatus.OK).body(returnValue);
    }

    //6. 이미지 수정 - html로 소개


    // 맴버 스킬 조회
    @GetMapping("/{memberId}/skill")
    public ResponseEntity<List<ResponseSkill>> getMemberSkill(@PathVariable long memberId){
        List<ResponseSkill> returnValue = mypageService.getMemberSkill(memberId);
        return ResponseEntity.status(HttpStatus.OK).body(returnValue);
    }

    // 맴버 스킬 추가
    @PutMapping("/{memberId}/skill")
    public ResponseEntity<List<ResponseSkill>> putMemberSkill(@PathVariable long memberId, @RequestBody RequestSkillId skillId){
        List<ResponseSkill> returnValue = mypageService.putMemberSkill(memberId, skillId);
        return ResponseEntity.status(HttpStatus.CREATED).body(returnValue);
    }
    //8. 맴버 스킬 삭제
    @DeleteMapping("/{memberId}/skill")
    public ResponseEntity<ResponseSkillList> deleteMemberSkill(@PathVariable long memberId, @RequestBody RequestSkillId skillId){
        ResponseSkillList returnValue = mypageService.deleteMemberSkill(memberId, skillId);
        return ResponseEntity.status(HttpStatus.CREATED).body(returnValue);
    }

    @GetMapping("/{memberId}/recruits")
    public ResponseEntity<List<ResponseRecruitCategory>> getMemberRecruitCategoryList(@PathVariable long memberId){
        List<ResponseRecruitCategory> respones = mypageService.getMemberRecruitCategoryList(memberId);
        return ResponseEntity.status(HttpStatus.OK).body(respones);
    }
    //9. 맴버 분야 추가
    @PostMapping("/{memberId}/recruits")
    public ResponseEntity<List<ResponseRecruitCategory>> putMemberRecruit(@PathVariable long memberId, @RequestBody RequestRecruitCategory recruitId ){
        System.out.println("맴버 추가");
        List<ResponseRecruitCategory> returnValue = mypageService.putMemberRecruitCategory(memberId, recruitId);
        return ResponseEntity.status(HttpStatus.CREATED).body(returnValue);
    }
    //10. 맴버 분야 삭제
    @DeleteMapping("/{memberId}/recruits")
    public ResponseEntity<List<ResponseRecruitCategory>> deleteMemberRecruit(@PathVariable long memberId, @RequestBody RequestRecruitCategory recruitId){
        List<ResponseRecruitCategory> returnValue = mypageService.deleteMemberRecruitCategory(memberId, recruitId);
        return ResponseEntity.status(HttpStatus.CREATED).body(returnValue);
    }








}
