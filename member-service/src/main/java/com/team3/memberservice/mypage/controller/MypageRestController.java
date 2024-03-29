package com.team3.memberservice.mypage.controller;

import com.team3.memberservice.career.dto.CareerDTO;
import com.team3.memberservice.img.ImageVO;
import com.team3.memberservice.img.dto.ImageDTO;
import com.team3.memberservice.mypage.dto.*;
import com.team3.memberservice.mypage.service.MypageService;
import com.team3.memberservice.skill.dao.SkillDAO;
import com.team3.memberservice.skill.dto.RequestSkillId;
import com.team3.memberservice.skill.dto.ResponseSkill;
import com.team3.memberservice.skill.dto.ResponseSkillList;
import com.team3.memberservice.skill.entity.SkillEntity;
import com.team3.memberservice.skill.service.SkillService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
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

    @Operation(summary = "마이페이지 조회 ", description = "맴버 프로필, 경력, 스킬, 모집군 카테고리를 불러옵니다.")
    @GetMapping("/{memberId}")
    public ResponseEntity<MypageDTO> getMypage(@PathVariable long memberId){
        MemberProfileDTO profile = mypageService.getMypage(memberId);
        DegreeDTO degreeDTO = mypageService.getDegree(memberId);
        List<CareerDTO> careerDTOList = mypageService.getCareerList(memberId);
        List<ResponseSkill> skillDTOS = mypageService.getMemberSkill(memberId);
        List<ResponseRecruitCategory> RecruitCategory = mypageService.getMemberRecruitCategoryList(memberId);
        MypageDTO mypageDTO = new MypageDTO(profile,degreeDTO,careerDTOList,skillDTOS,RecruitCategory);
        System.out.println("mypageDTO = " + mypageDTO);
        System.out.println("전송 완료.");
        return ResponseEntity.status(HttpStatus.CREATED).body(mypageDTO);
    }
    @Operation(summary = "회원정보(Info,Profile) 수정", description = "회원의 이름, 닉네임, 전화번호, 생년월일을 수정합니다.")
    @PutMapping("/{memberId}")
    public ResponseEntity<MemberProfileDTO> postMypage(@PathVariable long memberId, @RequestBody RequestMember member) {
        MemberProfileDTO returnValue = mypageService.PostMypage(member,memberId);
        return ResponseEntity.status(HttpStatus.CREATED).body(returnValue);
    }
    @Operation(summary = "학력 수정", description = "회원의 학력을 수정합니다.")
    @PutMapping("/{memberId}/degree")
    public ResponseEntity<ResponseDegree> postDegree(@PathVariable@Parameter(example = "6249388071526484416") long memberId, @RequestBody DegreeDTO degreeDTO){
        ResponseDegree returnValue = mypageService.postDegree(memberId, degreeDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(returnValue);
    }

    @Operation(summary = "경력 조회", description = "회원의 경력 리스트를 조회합니다.")
    @GetMapping("/{memberId}/careers")
    public ResponseEntity<List<CareerDTO>> getCareerList(@PathVariable long memberId){
        List<CareerDTO> returnValue = mypageService.getCareerList(memberId);
        return ResponseEntity.status(HttpStatus.OK).body(returnValue);
    }

    @Operation(summary = "경력 수정", description = "회원의 경력을 수정합니다.")
    @PutMapping("/{memberId}/career")
    public ResponseEntity<CareerDTO> postCareer(@PathVariable long memberId,@RequestBody CareerDTO careerDTO){
        CareerDTO returnValue =  mypageService.postCareer(careerDTO, memberId);
        return ResponseEntity.status(HttpStatus.CREATED).body(returnValue);
    }

    @Operation(summary = "경력 추가", description = "회원의 경력을 추가합니다.")
    @PostMapping("/{memberId}/career")
    public ResponseEntity<CareerDTO> putCareer(@PathVariable long memberId, @RequestBody CareerDTO careerDTO){
        CareerDTO returnValue = mypageService.putCareer(careerDTO,memberId);
        return ResponseEntity.status(HttpStatus.CREATED).body(returnValue);
    }

    @Operation(summary = "경력 삭제", description = "회원의 경력을 제거합니다.")
    @DeleteMapping("/{memberId}/career")
    public ResponseEntity<CareerDTO> deleteCareer(@PathVariable long memberId, @RequestBody CareerDTO careerDTO){

        CareerDTO returnValue = mypageService.deleteCareer(careerDTO,memberId);
        return ResponseEntity.status(HttpStatus.OK).body(returnValue);
    }



    @Operation(summary = "기술스택 조회", description = "회원의 기술스택을 조회합니다.")
    @GetMapping("/{memberId}/skill")
    public ResponseEntity<List<ResponseSkill>> getMemberSkill(@PathVariable long memberId){
        List<ResponseSkill> returnValue = mypageService.getMemberSkill(memberId);
        return ResponseEntity.status(HttpStatus.OK).body(returnValue);
    }

    @Operation(summary = "기술스택 추가", description = "회원의 기술스택을 추가합니다.")
    @PostMapping("/{memberId}/skill")
    public ResponseEntity<List<ResponseSkill>> postMemberSkill(@PathVariable long memberId, @RequestBody RequestSkillId skillId){
        List<ResponseSkill> returnValue = mypageService.putMemberSkill(memberId, skillId);
        return ResponseEntity.status(HttpStatus.CREATED).body(returnValue);
    }

    @PutMapping("/{memberId}/skill")
    public ResponseEntity putMemberSkill(@PathVariable long memberId, @RequestBody List<SkillEntity> skills) {
        System.out.println("skills = " + skills);
        mypageService.putMemberSkills(memberId,skills);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = "기술스택 삭제", description = "회원의 기술스택을 제거합니다.")
    @DeleteMapping("/{memberId}/skill")
    public ResponseEntity<ResponseSkillList> deleteMemberSkill(@PathVariable long memberId, @RequestBody RequestSkillId skillId){
        ResponseSkillList returnValue = mypageService.deleteMemberSkill(memberId, skillId);
        return ResponseEntity.status(HttpStatus.CREATED).body(returnValue);
    }

    @Operation(summary = "모집군 조회", description = "회원의 모집군카테고리를 조회합니다.")
    @GetMapping("/{memberId}/recruits")
    public ResponseEntity<List<ResponseRecruitCategory>> getMemberRecruitCategoryList(@PathVariable long memberId){
        List<ResponseRecruitCategory> respones = mypageService.getMemberRecruitCategoryList(memberId);
        return ResponseEntity.status(HttpStatus.OK).body(respones);
    }

    @Operation(summary = "모집군 추가", description = "회원의 모집군카테고리를 추기합니다.")
    @PostMapping("/{memberId}/recruits")
    public ResponseEntity<List<ResponseRecruitCategory>> putMemberRecruit(@PathVariable long memberId, @RequestBody RequestRecruitCategory recruitId ){
        System.out.println("맴버 추가");
        List<ResponseRecruitCategory> returnValue = mypageService.putMemberRecruitCategory(memberId, recruitId);
        return ResponseEntity.status(HttpStatus.CREATED).body(returnValue);
    }
    @Operation(summary = "모집군 제거", description = "회원의 모집군카테고리를 제거합니다.")
    @DeleteMapping("/{memberId}/recruits")
    public ResponseEntity<List<ResponseRecruitCategory>> deleteMemberRecruit(@PathVariable long memberId, @RequestBody RequestRecruitCategory recruitId){
        List<ResponseRecruitCategory> returnValue = mypageService.deleteMemberRecruitCategory(memberId, recruitId);
        return ResponseEntity.status(HttpStatus.CREATED).body(returnValue);
    }


    @GetMapping("/{memberId}/modify-image")
    public String showModifyImage(@PathVariable long memberId, Model model){
//        ImageDTO image = mypageService.getImage(memberId);
        model.addAttribute(memberId);
        return "mypage/modify-image";
    }
    @PostMapping("/{memberId}/mypage/modify-image")
    public String modifyImage(Model model, @RequestParam MultipartFile imgFile, RedirectAttributes rttr, @RequestParam long memberId) throws IOException {
        mypageService.modifyImageDTO(memberId,imgFile);
        return "redirect:/mypage/" + memberId;
    }

}
