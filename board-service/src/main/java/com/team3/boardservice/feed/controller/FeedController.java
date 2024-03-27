package com.team3.boardservice.feed.controller;

import com.team3.boardservice.feed.dto.FeedDTO;
import com.team3.boardservice.feed.dto.ImgDTO;
import com.team3.boardservice.feed.service.FeedService;
import com.team3.boardservice.feed.service.ImgService;
import com.team3.boardservice.feed.vo.FeedVO;
import com.team3.boardservice.feed.vo.ResponseFeedVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/feeds")
@Tag(name = "게시글", description = "전체조회, 상세조회, 생성, 수정, 삭제")
public class FeedController {
    private FeedService feedService;
    private ImgService imgService;



    @Autowired
    public FeedController(FeedService feedService, ImgService imgService) {
        this.feedService = feedService;
        this.imgService = imgService;
    }

    /* 게시물 전체 조회 -fin*/
    @Operation(summary = "게시글 전체 조회", description = "모든 사용자들이 작성했던 게시글이 모두 조회됩니다.")
    @GetMapping("/listFeed")
    public ResponseEntity<List<FeedDTO>> getAllFeeds() {
        List<FeedDTO> feeds = feedService.findByFeeds();
        return ResponseEntity.ok().body(feeds);
    }

    /* 맴버의 작성한 게시물 조회*/
    @Operation(summary = "멤버 게시글  조회", description = "모든 사용자들이 작성했던 게시글이 모두 조회됩니다.")
    @GetMapping("member/{memberId}")
    public ResponseEntity<List<ResponseFeedVO>> getMemberFeeds(@PathVariable long memberId){
        List<ResponseFeedVO> feeds = feedService.findMemberFeeds(memberId);
        return ResponseEntity.ok().body(feeds);
    }

    /* 게시물 상세 조회시 댓글 출력 -fin*/
    @Operation(summary = "게시물 상세조회", description = "게시물 상세 조회시 게시물 작성자가 등록한 이미지 및 글, 댓글이 상세하게 조회됩니다.")
    @GetMapping("/{id}")
    public ResponseEntity<FeedVO> getFeedById(@PathVariable int id) {
        FeedVO feed = feedService.findFeedById(id);
        System.out.println("feed = " + feed);
        return ResponseEntity.status(HttpStatus.OK).body(feed);
    }

    /* 게시물 생성 -fin */
    @Operation(summary = "게시물 생성", description = "게시글을 작성 및 여러 개의 이미지를 등록할 수 있습니다.")
    @PostMapping("/{memberId}")
    public ResponseEntity<FeedVO> createdFeed(@RequestBody FeedDTO feedDTO,
//                                              @RequestParam(name="file") List<MultipartFile> requestImgs,
                                              @PathVariable long memberId) throws IOException {
        System.out.println("게시물 생성 접근");
        System.out.println("feedDTO = " + feedDTO);
//        List<ImgDTO> imgDTOS = imgService.multiFileUpload(requestImgs);
        FeedVO createdFeed = feedService.createFeed(feedDTO, memberId);

        return ResponseEntity.ok().body(createdFeed);
    }

    /* 게시물 수정 -fin*/
    @Operation(summary = "게시물 수정", description = "작성자가 등록했던 게시글을 수정 및 등록했던 이미지 삭제와 새 이미지 등록할 수 있습니다.")
    @PutMapping("/{boardId}")
    public ResponseEntity<FeedVO> updateFeed(@PathVariable int boardId,
//                                             @RequestParam(required = false) List<MultipartFile> newImgs,
//                                             @RequestParam(required = false) List<String> removeImgIds,
                                             @RequestBody FeedDTO feedDTO) throws IOException {
//        // 수정시 새롭게 추가될 이미지 파일 처리
//        List<ImgDTO> addedImgs = null;
//        if(newImgs != null && !newImgs.isEmpty()) {
//            addedImgs = imgService.multiFileUpload(newImgs);
//        }
//
//        // 수정시 이미지 파일 삭제 처리
//        if(removeImgIds != null && !removeImgIds.isEmpty()) {
//            imgService.removeImages(removeImgIds);
//        }
//        // boardId를 feedDTO에 설정
//        feedDTO.setBoardId(boardId);
        System.out.println("feedDTO = " + feedDTO);
        // 서비스를 통해 게시물 수정
        FeedVO updatedFeed = feedService.modifyFeed(boardId, feedDTO /*,addedImgs, removeImgIds*/);
        // 수정된 게시물 반환
        return ResponseEntity.ok().body(updatedFeed);
    }

    /* 게시물 삭제 -fin*/
    @Operation(summary = "게시물 삭제", description = "작성자가 등록했던 게시글을 삭제할 수 있으며, 이 때 등록했던 이미지 및 댓글이 모두 삭제됩니다.")
    @DeleteMapping("/{boardId}")
    public ResponseEntity<String> removeFeed(@PathVariable int boardId) {

        feedService.removeFeed(boardId);
//        return "redirect:/feeds/listFeed";
        return ResponseEntity.ok("게시물 삭제 완료");
    }




}
