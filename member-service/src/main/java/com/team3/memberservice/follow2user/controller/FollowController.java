package com.team3.memberservice.follow2user.controller;

import com.team3.memberservice.follow2user.dto.FollowDTO;
import com.team3.memberservice.follow2user.service.FollowService;
import com.team3.memberservice.follow2user.aggregate.Follow;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/follow")
public class FollowController {

    private final FollowService followService;

    @Autowired
    public FollowController(FollowService followService) {
        this.followService = followService;
    }

    @GetMapping("/{followId}")
    public ResponseEntity<FollowDTO> findMenuByCode(@PathVariable int followId) {
        FollowDTO follow = followService.findFollowById(followId);
        return ResponseEntity.ok(follow);
    }

    @GetMapping("/list")
    public ResponseEntity<List<FollowDTO>> findFollowList() {
        List<FollowDTO> followList = followService.findFollowList();
        return ResponseEntity.ok(followList);
    }
    @Transactional
    @PostMapping("/add")
    public ResponseEntity<Follow> addFollow(@RequestParam Long followingId, @RequestParam Long followedId) {
        Follow follow = followService.addFollow(followingId, followedId);
        if (follow == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(follow);
    }

    //팔로잉 조회
    @GetMapping("/follows/{followingId}")
    public ResponseEntity<List<Follow>> getFollowedByFollowingId(@PathVariable Long followingId) {
        List<Follow> followedList = followService.getFollowedByFollowingId(followingId);
        return ResponseEntity.ok(followedList);
    }

    //팔로워 조회
    @GetMapping("/followers/{followedId}")
    public ResponseEntity<List<Follow>> getFollowersByFollowedId(@PathVariable Long followedId) {
        List<Follow> followersList = followService.getFollowersByFollowedId(followedId);
        return ResponseEntity.ok(followersList);
    }

    @PostMapping("/delete")
    public ResponseEntity<?> deleteFollow(@RequestParam int followId) {
        followService.deleteFollow(followId);
        return ResponseEntity.ok().build();
    }
}
