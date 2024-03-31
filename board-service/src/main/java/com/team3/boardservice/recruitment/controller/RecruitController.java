package com.team3.boardservice.recruitment.controller;

import com.team3.boardservice.mypage.entity.MemberRecruitCategoryEntity;
import com.team3.boardservice.mypage.entity.MemberRecruitCategoryId;
import com.team3.boardservice.recruitment.aggregate.MemberRecruitsInfoDTO;
import com.team3.boardservice.recruitment.aggregate.RecruitCategoryDTO;
import com.team3.boardservice.recruitment.aggregate.RecruitDTO;
import com.team3.boardservice.recruitment.service.RecruitService;
import com.team3.boardservice.recruitment.vo.RecruitVO;
import com.team3.boardservice.recruitment.vo.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recruit")
@Tag(name = "모집글", description = "모집글 등록, 수정, 삭제, 조회")
public class RecruitController {

    private final RecruitService recruitService;
    
    @Autowired
    public RecruitController(RecruitService recruitService) {
        this.recruitService = recruitService;
    }

    // 상세 페이지
    @GetMapping("/{recruitId}")
    @Operation(summary = "모집글 조회", description = "사용자는 해당 모집글의 상세 정보를 조회할 수 있습니다.")
    public ResponseEntity<RecruitDTO> findRecruitById(@PathVariable int recruitId) {

        RecruitDTO recruit = recruitService.findRecruitById(recruitId);

        return ResponseEntity.ok().body(recruit);
    }

    // 전체 목록 조회
    @GetMapping("/list")
    @Operation(summary = "모집글 목록 조회", description = "사용자는 전체 모집글 목록을 조회할 수 있습니다.")
    public ResponseEntity<List<RecruitVO>> findRecruitList() {

        List<RecruitVO> recruitList = recruitService.findRecruitList();

        return ResponseEntity.ok().body(recruitList);
    }

    //리스트로 변환
    @GetMapping("/recruit-category/{recruitId}")
    public ResponseEntity<List<RefRecruitCategoryVO>> findRecruitCategory(@PathVariable int recruitId) {

        List<RefRecruitCategoryVO> recruitCategoryList = recruitService.findRecruitCategory(recruitId);

        return ResponseEntity.ok().body(recruitCategoryList);
    }

    @GetMapping("/recruit-skill/{recruitId}")
    public ResponseEntity<List<RecruitSkillVO>> findRecruitSkill(@PathVariable int recruitId) {

        List<RecruitSkillVO> recruitSkillList = recruitService.findRecruitSkill(recruitId);

        return ResponseEntity.ok().body(recruitSkillList);
    }

    // 설명. 모집군 카테고리
//    @GetMapping(value = "/recruit_category", produces = "application/json; charset=UTF-8")
//    @ResponseBody
//    public List<RecruitCategoryDTO> findRecruitCategoryList() {return recruitService.findAllRecruitCategory();}

//    @GetMapping("/board-service/recruit/recruit-category-selected")
//    List<ResponseRecruitCategory> getRecruitCategory(List<ReqeuestRecruitCategory> request){
//        List<ResponseRecruitCategory> responseRecruitCategoryList = recruitService.getRecruitCategory(request);
//    }

    // 설명. 기술 카테고리
//    @GetMapping(value = "/skill_category", produces = "application/json; charset=UTF-8")
//    @ResponseBody
//    public List<SkillEntity> findSkillList() {return recruitService.findAllSkill();}

    @PostMapping("/regist")
    @Operation(summary = "모집글 등록", description = "사용자가 입력한 내용으로 모집글을 등록할 수 있습니다.")
    public ResponseEntity<String> registRecruit(@RequestBody RecruitVO recruit) {
        System.out.println(recruit);
        RecruitDTO recruitDTO = recruitService.registRecruit(recruit);


//        return ResponseEntity.ok(recruitDTO);
        return ResponseEntity.ok("/localhost:5173/recruit");
    }

    @PutMapping("/modify/{recruitId}")
    @Operation(summary = "모집글 수정", description = "사용자가 입력한 내용으로 해당 모집글을 수정할 수 있습니다.")
    public ResponseEntity<RecruitDTO> modifyRecruit(@PathVariable int recruitId, @RequestBody RecruitVO recruit) {

        RecruitDTO recruitDTO = recruitService.modifyRecruit(recruitId, recruit);

        System.out.println("recruitDTO = " + recruitDTO);
        return ResponseEntity.ok().body(recruitDTO);
    }

    @DeleteMapping("/{recruitId}")
    @Operation(summary = "모집글 삭제", description = "사용자는 해당 모집글을 삭제할 수 있습니다.")
    public String deleteRecruit(@PathVariable int recruitId) {


        recruitService.deleteRecruit(recruitId);

        return "redirect:/recruit/list";    // 리다이렉트 주소(모집글 목록?)
    }


    @GetMapping("/recruit-categories/{memberId}")
    List<ResponseRecruitCategory> getRecruitCategory(@PathVariable long memberId){
        List<ResponseRecruitCategory> response = recruitService.getMemberRecruitCategory(memberId);
        return response;
    }

    @PostMapping("/recruit-categories/{memberId}/{recruitId}")
    List<ResponseRecruitCategory> postRecruitCategory(@PathVariable long memberId, @PathVariable int recruitId){
        List<ResponseRecruitCategory> response = recruitService.postMemberRecruitCategery(memberId,recruitId);

        return response;
    }
    @DeleteMapping("/recruit-categories/{memberId}/{recruitId}")
    List<ResponseRecruitCategory> deleteRecruitCategory(@PathVariable long memberId, @PathVariable int recruitId){
        List<ResponseRecruitCategory> response = recruitService.deleteMemberRecruitCategery(memberId,recruitId);
        return response;
    }


    @Transactional
    @PutMapping("/recruit-categories/profile/{memberId}")
    ResponseEntity PutAllRecruitCategory(@PathVariable long memberId, @RequestBody List<RequestRecruitCategory> recruits){
        System.out.println("recruits = " + recruits);
        recruitService.putAllMemberRecruitCategery(memberId,recruits);

        return ResponseEntity.ok().build();
    }



    @GetMapping("/member/{memberId}")
    ResponseEntity<List<RecruitDTO>> getMemberRecruitList(@PathVariable long memberId) {
//        List<RecruitVO> response = recruitService.getMemberRecruitList(memberId);
        List<RecruitDTO> response = recruitService.getMemberRecruitList(memberId);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/recruit-categories")
    ResponseEntity<List<RecruitCategoryDTO>> getRecruitCategories(){
        List<RecruitCategoryDTO> returnValue = recruitService.getAllRecruitCategory();
        return ResponseEntity.ok().body(returnValue) ;
    }

}
