package com.team3.itability.recruitment.controller;

import com.team3.itability.mypage.entity.SkillEntity;
import com.team3.itability.recruitment.aggregate.RecruitCategoryDTO;
import com.team3.itability.recruitment.aggregate.RecruitDTO;
import com.team3.itability.recruitment.service.RecruitService;
import com.team3.itability.recruitment.vo.RecruitVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/recruit")
public class RecruitController {
    private final RecruitService recruitService;

    @Autowired
    public RecruitController(RecruitService recruitService) {
        this.recruitService = recruitService;
    }

    // 상세 페이지
    @GetMapping("/{recruitId}")
    public String findRecruitById(@PathVariable int recruitId, Model model) {

        RecruitDTO recruit = recruitService.findRecruitById(recruitId);
        model.addAttribute("recruit", recruit);

        return "recruit/detail";
    }

    // 목록 조회
    @GetMapping("/list")
    public String findRecruitList(Model model) {

        List<RecruitVO> recruitList = recruitService.findRecruitList();
        model.addAttribute("recruitList", recruitList);

        return "recruit/list";
    }

    // 추가
    @GetMapping("/regist")
    public void registPage() {}

    // 설명. 모집군 카테고리
    @GetMapping(value = "/recruit_category", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<RecruitCategoryDTO> findRecruitCategoryList() {return recruitService.findAllRecruitCategory();}

    // 설명. 기술 카테고리
    @GetMapping(value = "/skill_category", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<SkillEntity> findSkillList() {return recruitService.findAllSkill();}

    @PostMapping("/regist")
    public String registRecruit(RecruitDTO recruit, @RequestParam long memberId, @RequestParam int recruitCategoryId, @RequestParam int skillId) {

        recruitService.registRecruit(recruit, memberId, recruitCategoryId, skillId);

        return "redirect:/recruit/list";    // 리다이렉트 주소(모집글 목록?)
    }

    // 수정
    @GetMapping("/modify")
    public void modifyPage() {}

    @PostMapping("/modify")
    public String modifyRecruit(RecruitDTO recruit) {

        recruitService.modifyRecruit(recruit);

//        return "redirect:/recruit/" + recruit.getRecruitId();    // 리다이렉트 주소(모집글 상세 페이지?)
        return "redirect:/recruit/list";
    }

    // 삭제
    @GetMapping("/delete")
    public void deletePage() {}

    @PostMapping("/delete")
    public String deleteRecruit(@RequestParam int recruitId) {

        recruitService.deleteRecruit(recruitId);

        return "redirect:/recruit/list";    // 리다이렉트 주소(모집글 목록?)
    }
}
