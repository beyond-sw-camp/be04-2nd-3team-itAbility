package com.team3.boardservice.feed.controller;

import com.team3.boardservice.feed.dto.FeedDTO;
import com.team3.boardservice.feed.service.FeedService;
import com.team3.boardservice.feed.vo.FeedVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/feeds")
public class FeedController {

    @Autowired
    private FeedService feedService;

    /* 게시물 전체 조회 -fin*/
    @GetMapping("/listFeed")
    public ResponseEntity<String> getAllFeeds() {
        List<FeedDTO> feeds = feedService.findByFeeds();
        return ResponseEntity.ok().body("OK");
    }

    /* 게시물 상세 조회시 댓글 출력 -fin*/
    @GetMapping("/{id}")
    public ResponseEntity<FeedVO> getFeedById(@PathVariable int id) {
        FeedVO feed = feedService.findFeedById(id);
        System.out.println("feed = " + feed);
        return ResponseEntity.status(HttpStatus.OK).body(feed);
    }

    /* 게시물 생성 -fin */
    @PostMapping("/{memberId}")
    public ResponseEntity<FeedVO> createdFeed(@RequestBody FeedDTO feedDTO, @PathVariable long memberId) {
        System.out.println("게시물 생성 접근");
        System.out.println("feedDTO = " + feedDTO);
        FeedVO createdFeed = feedService.createFeed(feedDTO, memberId);
        return ResponseEntity.ok().body(createdFeed);
    }

    /* 게시물 수정 -fin*/
    @PutMapping("/{boardId}")
    public ResponseEntity<FeedVO> updateFeed(@PathVariable int boardId, @RequestBody FeedDTO feedDTO) {
        // boardId를 feedDTO에 설정
        feedDTO.setBoardId(boardId);
        // 서비스를 통해 게시물 수정
        FeedVO updatedFeed = feedService.modifyFeed(feedDTO);
        // 수정된 게시물 반환
        return ResponseEntity.ok().body(updatedFeed);
    }

    /* 게시물 삭제 -fin*/
    @DeleteMapping("/{boardId}")
    public String removeFeed(@PathVariable int boardId) {
        feedService.removeFeed(boardId);
        return "redirect:/feeds/listFeed";
    }
}
