package com.team3.itability.reple.controller;

import com.team3.itability.reple.dto.CommentDTO;
import com.team3.itability.reple.service.CommentService;
import com.team3.itability.reple.vo.RequestCommentVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comments")
@Slf4j
public class CommentController {

    @Autowired
    CommentService commentService;


    /* 댓글 생성 */
    @PostMapping("/add")
    public ResponseEntity<CommentDTO> createComment(@PathVariable("cmtId") int cmtId, @RequestBody RequestCommentVO requestCommentVO) {
        CommentDTO createdComment = commentService.createComment(cmtId, requestCommentVO);
        return new ResponseEntity<>(createdComment, HttpStatus.CREATED);

    }

    /* 댓글 수정 */
    @PutMapping("/{commentId}")
    public ResponseEntity<CommentDTO> modifyComment(@PathVariable("commentId") int commentId, @RequestBody RequestCommentVO requestCommentVO) {

        CommentDTO modifiedComment = commentService.modifyComment(commentId, requestCommentVO);

        return new ResponseEntity<>(modifiedComment, HttpStatus.OK);

    }

    /* 댓글 삭제 */
    @DeleteMapping("/{commentId}")
    public ResponseEntity<CommentDTO> removeComment(@PathVariable int commentId, Long memberId) {

        commentService.removeComment(commentId, memberId);
        return ResponseEntity.ok().build();
    }

}
