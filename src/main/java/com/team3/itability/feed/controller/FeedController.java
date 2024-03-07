package com.team3.itability.feed.controller;

import com.team3.itability.feed.dto.FeedDTO;
import com.team3.itability.feed.service.FeedService;
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

    /* 게시물 전체 조회 */
    @GetMapping("/listFeed")
    public ResponseEntity<List<FeedDTO>> getAllFeeds() {
        List<FeedDTO> feeds = feedService.findByFeeds();
        return ResponseEntity.ok().body(feeds);
    }

    /* 게시물 상세 조회시 댓글 출력 */
    @GetMapping("/{id}")
    public ResponseEntity<FeedDTO> getFeedById(@PathVariable int id) {
        FeedDTO feed = feedService.findFeedById(id);

        return new ResponseEntity<>(feed, HttpStatus.OK);
    }

    /* 게시물 생성 */
    @PostMapping
    public ResponseEntity<FeedDTO> createdFeed(Model model, @ModelAttribute FeedDTO feedDTO, @RequestParam long memberId) {
        FeedDTO createdFeed = feedService.createFeed(feedDTO, memberId);
        return ResponseEntity.ok().body(createdFeed);
    }

    /* 게시물 수정 */
    @PutMapping("/{boardId}")
    public ResponseEntity<FeedDTO> updateFeed(@PathVariable int boardId, @RequestBody FeedDTO feedDTO) {

        // boardId를 feedDTO에 설정
        feedDTO.setBoardId(boardId);

        // 서비스를 통해 게시물 수정
        FeedDTO updatedFeed = feedService.modifyFeed(feedDTO);

        // 수정된 게시물 반환
        return ResponseEntity.ok(updatedFeed);
    }


    /* 게시물 삭제 */
    @GetMapping("/delete")
    public void removeFeeds() {}

    @PostMapping("/delete")
    public String removeFeed(@RequestParam int boardId) {
        feedService.removeFeed(boardId);

        return "redirect:/feeds/listFeed";
    }
}
