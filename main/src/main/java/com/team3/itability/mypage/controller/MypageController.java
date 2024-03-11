package com.team3.itability.mypage.controller;

import com.team3.itability.img.dto.ImageDTO;
import com.team3.itability.mypage.dto.*;
import com.team3.itability.mypage.service.MypageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("/mypage")
public class MypageController {
    private final MypageService mypageService;
    @Autowired
    public MypageController(MypageService mypageService) {
        this.mypageService = mypageService;
    }

    /**
     * <h1>MyPage</h1>
     * 마이페이지 컨트롤러
     * */
    @GetMapping("/{memberId}")
    public String getMypage(@PathVariable long memberId, Model model){
        MemberProfileDTO profile = mypageService.printMypage(memberId);
        System.out.println("profile = " + profile);
        List<CareerDTO> careerDTOList = mypageService.printCareerList(memberId);
        MemberAndRemainSkillDTO skillDTOS = mypageService.printMemberSkillList(memberId);
        MemberAndRemainRecruitCategoryDTO RecruitCategoryDTOS = mypageService.printMemberRecruitList(memberId);
        model.addAttribute("recruits", RecruitCategoryDTOS);
        model.addAttribute("skill",skillDTOS);
        model.addAttribute("careerList", careerDTOList);
        model.addAttribute("profile", profile);
        return "mypage/mypage";
    }
    /**
     * <h1>modify-name</h1>
     * 개인정보 수정 컨트롤러
     * */
    @GetMapping("/{memberId}/modify-name")
    public String modifyMypage(Model model, @PathVariable long memberId){
        MemberProfileDTO profile = mypageService.printMypage(memberId);
        model.addAttribute("profile", profile);
        return "mypage/modify";
    }
    @PostMapping("/modify-name")
    public String modify(Model model, @RequestParam long memberId, @RequestParam String nickname, @RequestParam String name, @RequestParam String phone, @RequestParam String birthDate){
        mypageService.modifyMypage(memberId,nickname,name,phone,birthDate);
        model.addAttribute(memberId);
        String redirectUrl = "redirect:/mypage/" + memberId;
        return redirectUrl;
    }
    /**
     * <h1>modify-degree</h1>
     * 학력 수정 컨트롤러
     * */
    @GetMapping("/{memberId}/modify-degree")
    public String modifyDegree(Model model, @PathVariable long memberId){
        MemberProfileDTO profile = mypageService.printMypage(memberId);
        model.addAttribute("profile", profile);
        return "mypage/modify-degree";
    }
    @PostMapping("/modify-degree")
    public String modifySubmitDegree(Model model, @RequestParam long memberId, @ModelAttribute DegreeDTO degreeDTO){
        mypageService.modifyDegree(memberId, degreeDTO);
        String redirectUrl = "redirect:/mypage/" + memberId;
        return redirectUrl;
    }

    /**<h1>modify-career</h1>
     * 경력 수정 컨트롤러*/
    @GetMapping("/{memberId}/career-list")
    public String printCareerList(Model model, @PathVariable long memberId){
        List<CareerDTO> careerList = mypageService.printCareerList(memberId);
        model.addAttribute("memberId",memberId);
        model.addAttribute("careerList", careerList);
        return "mypage/career-list";
    }
    @GetMapping("/{memberId}/modify-career/{careerId}")
    public String modifyCareer(Model model, @PathVariable long memberId, @PathVariable int careerId){
        CareerDTO careerDTO = mypageService.printCareer(careerId);
        model.addAttribute("career", careerDTO);
        model.addAttribute("memberId",memberId);
        return "mypage/modify-career";
    }
    @PostMapping("/modify-career")
    public String modifySubmitCareer(Model model, @ModelAttribute CareerDTO careerDTO){
        mypageService.modifyCareer(careerDTO);
        return "redirect:/mypage/" + careerDTO.getMemberId().getMemberId();
    }
    @GetMapping("/{memberId}/add-career")
    public String showAddCareer(Model model, @PathVariable long memberId){
        model.addAttribute(memberId);
        return "mypage/add-career";
    }
    @PostMapping("/add-career")
    public String AddCareer(Model model, @ModelAttribute CareerDTO careerDTO, @RequestParam long memberId){
        mypageService.addCareer(careerDTO,memberId);
        System.out.println("career = " + careerDTO);
        return "redirect:/mypage/" + memberId;
    }

    /**<h2>modify-Image</h2>*/
    @GetMapping("/{memberId}/modify-image")
    public String showModifyImage(@PathVariable long memberId, Model model){
        ImageDTO image = mypageService.getImage(memberId);
        model.addAttribute("image",image);
        model.addAttribute(memberId);
        return "mypage/modify-image";
    }
    @PostMapping("/{memberId}/mypage/modify-image")
    public String modifyImage(Model model, @RequestParam MultipartFile imgFile, RedirectAttributes rttr, @RequestParam long memberId) throws IOException {
        mypageService.modifyImageDTO(memberId,imgFile);
        return "redirect:/mypage/" + memberId;
    }

    /**<h1>modify-MemberSkill</h1>*/
    @GetMapping("/{memberId}/member-skill-list")
    public String showMemberSkillList(@PathVariable Long memberId, Model model ){
        MemberAndRemainSkillDTO skill = mypageService.printMemberSkillList(memberId);
        model.addAttribute("skill",skill);
        return "mypage/member-skill-list";
    }

    @Transactional
    @GetMapping("{memberId}/remove-skill/{skillId}")
    public String removeSkill(@PathVariable long memberId, @PathVariable int skillId, Model model){
        mypageService.removeMemberSkill(memberId,skillId);
        return "redirect:/mypage/" + memberId + "/member-skill-list";
    }
    @Transactional
    @GetMapping("{memberId}/add-skill/{skillId}")
    public String addSkill(@PathVariable long memberId, @PathVariable int skillId, Model model){
        mypageService.addMemberSkill(memberId,skillId);
        return "redirect:/mypage/" + memberId + "/member-skill-list";
    }

    /**<h1>Modify Member Recruit Category</h1>*/
    @GetMapping("{memberId}/memberRecruitCategory")
    public String printMemberRecruitCategory(@PathVariable Long memberId, Model model ) {
        MemberAndRemainRecruitCategoryDTO RecruitCategory = mypageService.printMemberRecruitList(memberId);
        model.addAttribute("recruits", RecruitCategory);
        return "mypage/member-recruit-category-list";
    }
    @Transactional
    @GetMapping("{memberId}/remove-recruit-category/{recruitCategory}")
    public String removeMemberRecruitCategory(@PathVariable long memberId, @PathVariable int recruitCategory, Model model){
        mypageService.removeMemberRecruitCategory(memberId,recruitCategory);
        System.out.println("이전 페이지로 돌아갑니다.");
        return "redirect:/mypage/" + memberId + "/memberRecruitCategory";
    }
    @Transactional
    @GetMapping("{memberId}/add-recruit-category/{recruitCategory}")
    public String addMemberRecruitCategory(@PathVariable long memberId, @PathVariable int recruitCategory, Model model){
        mypageService.addMemberRecruitCategory(memberId,recruitCategory);
        return "redirect:/mypage/" + memberId + "/memberRecruitCategory";
    }

}
