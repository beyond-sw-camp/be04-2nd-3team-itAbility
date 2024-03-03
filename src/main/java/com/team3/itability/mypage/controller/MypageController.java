package com.team3.itability.mypage.controller;

import com.team3.itability.member.dto.MemberInfoDTO;
import com.team3.itability.mypage.dto.CareerDTO;
import com.team3.itability.mypage.dto.DegreeDTO;
import com.team3.itability.mypage.dto.ImageDTO;
import com.team3.itability.mypage.dto.MemberProfileDTO;
import com.team3.itability.mypage.service.MypageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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
    @GetMapping("/{memberCode}")
    public String printMypage(@PathVariable int memberCode, Model model){
        MemberProfileDTO profile = mypageService.printMypageData(memberCode);
        List<CareerDTO> careerList = mypageService.printCareerList(memberCode);
        model.addAttribute("careerList",careerList);
        model.addAttribute("profile", profile);
        return "mypage/mypage";
    }
    /**
     * <h1>modify-name</h1>
     * 이름, 닉네임 변경 컨트롤러
     * */
    @GetMapping("/{memberCode}/modify-name")
    public String modifyMypage(Model model, @PathVariable int memberCode){
        MemberProfileDTO profile = mypageService.printMypageData(memberCode);
        model.addAttribute("profile", profile);
        return "mypage/modify";
    }
    @PostMapping("/modify-name")
    public String modify(Model model, @RequestParam int memberCode, @RequestParam String nickname, @RequestParam String name){
        MemberProfileDTO profile = mypageService.modifyMypage(memberCode,nickname,name);
        model.addAttribute(memberCode);
        String redirectUrl = "redirect:/mypage/" + memberCode;
        return redirectUrl;
    }
    /**
     * <h1>modify-degree</h1>
     * 학력 수정 컨트롤러
     * */
    @GetMapping("/{memberCode}/modify-degree")
    public String modifyDegree(Model model, @PathVariable int memberCode){
        MemberProfileDTO profile = mypageService.printMypageData(memberCode);
        model.addAttribute("profile", profile);
        return "mypage/modify-degree";
    }
    @PostMapping("/modify-degree")
    public String modifySubmitDegree(Model model, @RequestParam int memberId, @ModelAttribute DegreeDTO degreeDTO){
        MemberProfileDTO profile = mypageService.modifyDegree(memberId, degreeDTO);
        model.addAttribute(profile);
        String redirectUrl = "redirect:/mypage/" + profile.getMemberId();
        return redirectUrl;
    }

    /**<h1>modify-career</h1>
     * 경력 수정 컨트롤러*/
    @GetMapping("/{memberCode}/career-list")
    public String printCareerList(Model model, @PathVariable int memberCode){
        List<CareerDTO> careerList = mypageService.printCareerList(memberCode);
        model.addAttribute("memberCode",memberCode);
        model.addAttribute("careerList",careerList);
        return "mypage/career-list";
    }
    @GetMapping("/{memberCode}/modify-career/{careerId}")
    public String modifyCareer(Model model, @PathVariable int memberCode, @PathVariable int careerId){
        CareerDTO career = mypageService.printCareer(careerId);
        model.addAttribute("career", career);
        model.addAttribute("memberCode",memberCode);
        return "mypage/modify-career";
    }
    @PostMapping("/modify-career")
    public String modifySubmitCareer(Model model, @ModelAttribute CareerDTO careerDTO){
        System.out.println("careerDTO = " + careerDTO);
        CareerDTO career = mypageService.modifyCareer(careerDTO);
        System.out.println("career = " + career);
        return "redirect:/mypage/" + careerDTO.getMemberId().getMemberId();
    }


}
