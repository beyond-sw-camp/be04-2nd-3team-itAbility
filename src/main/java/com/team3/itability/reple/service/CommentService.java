package com.team3.itability.reple.service;

import com.team3.itability.feed.dto.FeedDTO;
import com.team3.itability.feed.repository.FeedRepo;
import com.team3.itability.member.dao.MemberInfoRepo;
import com.team3.itability.member.dto.MemberInfoDTO;
import com.team3.itability.reple.aggregate.CommentEntity;
import com.team3.itability.reple.dto.CommentDTO;
import com.team3.itability.reple.repository.CommentRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public CommentDTO createComment(int boardId, CommentDTO commentDTO) {
        ModelMapper modelMapper = new ModelMapper();
        CommentEntity commentEntity = modelMapper.map(commentDTO, CommentEntity.class);
        FeedDTO feed = feedRepo.findById(boardId).orElseThrow();
        CommentEntity savedComment = commentRepo.save(commentEntity);

        return modelMapper.map(savedComment, CommentDTO.class);
    }

    /* 댓글 수정 */
    @Transactional
    public CommentDTO modifyComment(int cmtId, Long memberId, CommentDTO commentDTO) {
        CommentEntity comment = commentRepo.findById(cmtId).orElseThrow();
        MemberInfoDTO memberInfo = memberInfoRepo.findById(memberId).orElseThrow();

        /* 추후 개발 예정. 자신이 작성한 댓글이 아닐 경우 수정 불가능 */
//        if (!comment.getMemberId().equals(memberId)) {
//            throw new RuntimeException("자신이 작성한 댓글만 수정 가능합니다.");
//        }

        comment.setCmtContent(commentDTO.getCmtContent());
        CommentEntity modifyComment = commentRepo.save(comment);

        return modelMapper.map(modifyComment, CommentDTO.class);
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
