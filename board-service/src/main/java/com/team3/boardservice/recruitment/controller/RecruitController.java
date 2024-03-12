package com.team3.boardservice.recruitment.controller;

import com.team3.boardservice.recruitment.aggregate.RecruitDTO;
import com.team3.boardservice.recruitment.service.RecruitService;
import com.team3.itability.recruitment.vo.RecruitVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recruit")
public class RecruitController {

    private final RecruitService recruitService;

    @Autowired
    public RecruitController(RecruitService recruitService) {
        this.recruitService = recruitService;
    }

    // 상세 페이지
    @GetMapping("/{recruitId}")
    public ResponseEntity<RecruitDTO> findRecruitById(@PathVariable int recruitId) {

        RecruitDTO recruit = recruitService.findRecruitById(recruitId);

        return ResponseEntity.ok().body(recruit);
    }

    // 전체 목록 조회
    @GetMapping("/list")
    public ResponseEntity<List<RecruitVO>> findRecruitList() {

        List<RecruitVO> recruitList = recruitService.findRecruitList();

        return ResponseEntity.ok().body(recruitList);
    }

    // 설명. 모집군 카테고리
//    @GetMapping(value = "/recruit_category", produces = "application/json; charset=UTF-8")
//    @ResponseBody
//    public List<RecruitCategoryDTO> findRecruitCategoryList() {return recruitService.findAllRecruitCategory();}

    // 설명. 기술 카테고리
//    @GetMapping(value = "/skill_category", produces = "application/json; charset=UTF-8")
//    @ResponseBody
//    public List<SkillEntity> findSkillList() {return recruitService.findAllSkill();}

    @PostMapping("/regist")
    public ResponseEntity<RecruitDTO> registRecruit(@RequestBody RecruitVO recruit) {

        RecruitDTO recruitDTO = recruitService.registRecruit(recruit);

        return ResponseEntity.status(HttpStatus.CREATED).body(recruitDTO);
    }

    @PutMapping("/modify/{recruitId}")
    public ResponseEntity<RecruitDTO> modifyRecruit(@PathVariable int recruitId, @RequestBody RecruitVO recruit) {

        RecruitDTO recruitDTO = recruitService.modifyRecruit(recruitId, recruit);

        System.out.println("recruitDTO = " + recruitDTO);
        return ResponseEntity.ok().body(recruitDTO);
    }

    @DeleteMapping("/{recruitId}")
    public String deleteRecruit(@PathVariable int recruitId) {

        recruitService.deleteRecruit(recruitId);

        return "redirect:/recruit/list";    // 리다이렉트 주소(모집글 목록?)
    }
}
