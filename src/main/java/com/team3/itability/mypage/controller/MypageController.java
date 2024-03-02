package com.team3.itability.mypage.controller;

import com.team3.itability.member.dto.MemberInfoDTO;
import com.team3.itability.mypage.dto.DegreeDTO;
import com.team3.itability.mypage.dto.ImageDTO;
import com.team3.itability.mypage.dto.MemberProfileDTO;
import com.team3.itability.mypage.service.MypageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/mypage")
public class MypageController {
    private final MypageService mypageService;
    @Autowired
    public MypageController(MypageService mypageService) {
        this.mypageService = mypageService;
    }
    @GetMapping("/{memberCode}")
    public String printMypage(@PathVariable int memberCode, Model model){
        MemberProfileDTO profile = mypageService.printMypageData(memberCode);
        model.addAttribute("profile", profile);
        return "mypage/mypage";
    }
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
    @GetMapping("/{memberCode}/modify-degree")
    public String modifyDegree(Model model, @PathVariable int memberCode){
        MemberProfileDTO profile = mypageService.printMypageData(memberCode);
        model.addAttribute("profile", profile);
        return "mypage/modify-degree";
    }
    @PostMapping("/modify-degree")
    public String modify(Model model, @RequestParam int memberId, @ModelAttribute DegreeDTO degreeDTO){
        MemberProfileDTO profile = mypageService.modifyDegree(memberId, degreeDTO);
        model.addAttribute(profile);
        String redirectUrl = "redirect:/mypage/" + profile.getMemberId();
        return redirectUrl;
    }


}
