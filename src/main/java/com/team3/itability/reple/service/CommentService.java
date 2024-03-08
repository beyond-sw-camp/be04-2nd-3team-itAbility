package com.team3.itability.reple.service;

import com.team3.itability.feed.dto.FeedDTO;
import com.team3.itability.feed.repository.FeedRepo;
import com.team3.itability.member.dao.MemberInfoRepo;
import com.team3.itability.member.dto.MemberInfoDTO;
import com.team3.itability.reple.aggregate.CommentEntity;
import com.team3.itability.reple.dto.CommentDTO;
import com.team3.itability.reple.repository.CommentRepo;
import com.team3.itability.reple.vo.RequestCommentVO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CommentService {

    private FeedRepo feedRepo;


    private CommentRepo commentRepo;


    private MemberInfoRepo memberInfoRepo;

    private ModelMapper modelMapper;


    @Autowired
    public CommentService(FeedRepo feedRepo,
                          CommentRepo commentRepo,
                          MemberInfoRepo memberInfoRepo,
                          ModelMapper modelMapper) {
        this.feedRepo = feedRepo;
        this.commentRepo = commentRepo;
        this.memberInfoRepo = memberInfoRepo;
        this.modelMapper = modelMapper;
    }

    /* 댓글 생성 */
    @Transactional
    public CommentDTO createComment(int boardId, RequestCommentVO requestCommentVO) {
        CommentEntity commentEntity = modelMapper.map(requestCommentVO, CommentEntity.class);
        FeedDTO feed = feedRepo.findById(requestCommentVO.getBoardId()).orElseThrow();
        CommentEntity savedComment = commentRepo.save(commentEntity);

        return modelMapper.map(savedComment, CommentDTO.class);
    }

    /* 댓글 수정 */

    @Transactional
    public CommentDTO modifyComment(int cmtId, RequestCommentVO requestCommentVO) {

        CommentEntity comment = commentRepo.findById(cmtId)
                                           .orElseThrow(() -> new ResponseStatusException(
                                                   HttpStatus.NOT_FOUND, "댓글을 찾을 수 없습니다."));
        MemberInfoDTO memberInfo = memberInfoRepo.findById( requestCommentVO.getMemberId())
                                                 .orElseThrow(() -> new ResponseStatusException(
                                                         HttpStatus.NOT_FOUND, "멤버 정보를 찾을 수 없습니다."));

//        /* 추후 개발 예정. 자신이 작성한 댓글이 아닐 경우 수정 불가능 */
//        if (!comment.getMemberId().equals(requestCommentVO.getMemberId())) {
//            throw new RuntimeException("자신이 작성한 댓글만 수정 가능합니다.");
//        }

        comment.setCmtContent(requestCommentVO.getCmtContent());
        CommentEntity modifiedComment  = commentRepo.save(comment);

        return modelMapper.map(modifiedComment, CommentDTO.class);
    }

    /* 댓글 삭제 */
    @Transactional
    public void removeComment(int commentId, Long memberId) {
        CommentEntity commentEntity = commentRepo.findById(commentId).orElseThrow();

        /* 추후 개발 예정. 자신이 작성한 댓글이 아닐 경우 삭제 불가능 */
//        if (!commentEntity.getMemberId().equals(memberId)) {
//            throw new RuntimeException("자신이 작성한 댓글만 삭제 가능합니다.");
//        }

        commentRepo.deleteById(commentId);

    }


}
