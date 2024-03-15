package com.team3.boardservice.feed.controller;

import com.team3.boardservice.feed.dto.FeedDTO;
import com.team3.boardservice.feed.dto.ImgDTO;
import com.team3.boardservice.feed.service.FeedService;
import com.team3.boardservice.feed.service.ImgService;
import com.team3.boardservice.feed.vo.FeedVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/feeds")
public class FeedController {


    private FeedService feedService;

    private ImgService imgService;

    @Autowired
    public FeedController(FeedService feedService, ImgService imgService) {
        this.feedService = feedService;
        this.imgService = imgService;
    }

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
    public ResponseEntity<FeedVO> createdFeed(@RequestBody FeedDTO feedDTO,
                                              @RequestParam(name="file") List<MultipartFile> requestImgs,
                                              @PathVariable long memberId) throws IOException {
        System.out.println("게시물 생성 접근");
        System.out.println("feedDTO = " + feedDTO);
        List<ImgDTO> imgDTOS = imgService.multiFileUpload(requestImgs);
        FeedVO createdFeed = feedService.createFeed(feedDTO, memberId, imgDTOS);

        return ResponseEntity.ok().body(createdFeed);
    }

    /* 게시물 수정 -fin*/
//    @PutMapping("/{boardId}")
    @PostMapping("/{boardId}/updateFeed")
    public ResponseEntity<FeedVO> updateFeed(@ModelAttribute int boardId,
                                             @RequestParam(required = false) List<MultipartFile> newImgs,
                                             @RequestParam(required = false) List<String> removeImgIds,
                                             @ModelAttribute FeedDTO feedDTO) throws IOException {
        // 수정시 새롭게 추가될 이미지 파일 처리
        List<ImgDTO> addedImgs = null;
        if(newImgs != null && !newImgs.isEmpty()) {
            addedImgs = imgService.multiFileUpload(newImgs);
        }

        // 수정시 이미지 파일 삭제 처리
        if(removeImgIds != null && !removeImgIds.isEmpty()) {
            imgService.removeImages(removeImgIds);
        }
//        // boardId를 feedDTO에 설정
//        feedDTO.setBoardId(boardId);

        // 서비스를 통해 게시물 수정
        FeedVO updatedFeed = feedService.modifyFeed(boardId, feedDTO, addedImgs, removeImgIds);
        // 수정된 게시물 반환
        return ResponseEntity.ok().body(updatedFeed);
    }

    /* 게시물 삭제 -fin*/
    @DeleteMapping("/{boardId}")
    public ResponseEntity<String> removeFeed(@PathVariable int boardId) {
        feedService.removeFeed(boardId);
//        return "redirect:/feeds/listFeed";
        return ResponseEntity.ok("게시물 삭제 완료");
    }

}
