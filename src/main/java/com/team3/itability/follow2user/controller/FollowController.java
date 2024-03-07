package com.team3.itability.follow2user.controller;


import com.team3.itability.follow2user.aggregate.Follow;
import com.team3.itability.follow2user.dto.FollowDTO;
import com.team3.itability.follow2user.service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/follow")
public class FollowController {

    private final FollowService followService;

    @Autowired
    public FollowController(FollowService followService) {
        this.followService = followService;
    }

    @GetMapping("/{followId}")
    public String findMenuByCode(@PathVariable int followId, Model model) {
        FollowDTO follow = followService.findFollowById(followId);
        model.addAttribute("follow", follow);

        return "follow/detail";
    }

    @GetMapping("/list")
    public String findFollowList(Model model) {

        List<FollowDTO> followList = followService.findFollowList();
        model.addAttribute("followList", followList);

        return "follow/list";
    }

    @GetMapping("/add")
    public void addPage(){}

    @PostMapping("/add")
    public String addFollow(@RequestParam Long followingId, @RequestParam Long followedId, RedirectAttributes redirectAttributes) {
        Follow follow = followService.addFollow(followingId, followedId);
        if (follow == null) {;
            return "redirect:/follow/add";
        }
        return "redirect:/follow/list";
    }

    @GetMapping("/follows/{followingId}")
    public String getFollowedByFollowingId(@PathVariable Long followingId, Model model) {
        List<Follow> followedList = followService.getFollowedByFollowingId(followingId);
        model.addAttribute("followedList", followedList);
        return "follow/followedList"; // Thymeleaf 템플릿의 경로를 반환
    }

    @GetMapping("/delete")
    public void deleteFollowPage(){}


    @PostMapping("/delete")
    public String deleteFollow(@RequestParam int followId) {
        followService.deleteFollow(followId);

        return "redirect:/follow/list";
    }


}
