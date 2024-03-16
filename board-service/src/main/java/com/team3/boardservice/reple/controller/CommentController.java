package com.team3.boardservice.reple.controller;


import com.team3.boardservice.reple.dto.CommentDTO;
import com.team3.boardservice.reple.service.CommentService;

import com.team3.boardservice.reple.vo.RequestCommentVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comments")
@Tag(name = "댓글", description = "생성, 수정, 삭제")
public class CommentController {

    @Autowired
    CommentService commentService;


    /* 댓글 생성 */
    @Operation(summary = "댓글 생성", description = "사용자가 보고 있는 게시글에 댓글을 생성할 수 있습니다.")
    @PostMapping("/add")
    public ResponseEntity<CommentDTO> createComment(@PathVariable("cmtId") int cmtId, @RequestBody RequestCommentVO requestCommentVO) {
        CommentDTO createdComment = commentService.createComment(cmtId, requestCommentVO);
        return new ResponseEntity<>(createdComment, HttpStatus.CREATED);

    }

    /* 댓글 수정 */
    @Operation(summary = "댓글 수정", description = "사용자가 생성했던 댓글을 수정할 수 있습니다.")
    @PutMapping("/{commentId}")
    public ResponseEntity<CommentDTO> modifyComment(@PathVariable("commentId") int commentId, @RequestBody RequestCommentVO requestCommentVO) {

        CommentDTO modifiedComment = commentService.modifyComment(commentId, requestCommentVO);

        return new ResponseEntity<>(modifiedComment, HttpStatus.OK);

    }

    /* 댓글 삭제 */
    @Operation(summary = "댓글 삭제", description = "사용자가 생성했던 댓글을 삭제할 수 있습니다.")
    @DeleteMapping("/{commentId}")
    public ResponseEntity<CommentDTO> removeComment(@PathVariable int commentId, Long memberId) {

        commentService.removeComment(commentId, memberId);
        return ResponseEntity.ok().build();
    }

}
