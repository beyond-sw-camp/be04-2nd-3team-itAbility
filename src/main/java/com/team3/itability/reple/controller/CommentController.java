package com.team3.itability.reple.controller;

import com.team3.itability.reple.dto.CommentDTO;
import com.team3.itability.reple.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    CommentService commentService;

    /* 댓글 생성 */
    @PostMapping
    public ResponseEntity<CommentDTO> createComment(@RequestParam int boardId, @RequestBody CommentDTO commentDTO) {
        CommentDTO createdComment = commentService.createComment(boardId, commentDTO);
        return new ResponseEntity<>(createdComment, HttpStatus.CREATED);
    }

    /* 댓글 수정 */
    @PutMapping("{commentId}")
    public ResponseEntity<CommentDTO> modifyComment(@PathVariable int commentId, Long memberId, @RequestBody CommentDTO commentDTO) {

        CommentDTO modifyComment = commentService.modifyComment(commentId, memberId, commentDTO);

        return ResponseEntity.ok(modifyComment);

    }

    /* 댓글 삭제 */
    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> removeComment(@PathVariable int commentId, Long memberId) {

        commentService.removeComment(commentId, memberId);
        return ResponseEntity.ok().build();
    }

}
