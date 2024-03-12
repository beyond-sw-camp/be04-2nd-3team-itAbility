package com.team3.boardservice.feed.service;

import com.team3.boardservice.feed.dto.FeedDTO;

import com.team3.boardservice.feed.repository.FeedRepo;
import com.team3.boardservice.reple.aggregate.CommentEntity;
import com.team3.boardservice.reple.repository.CommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class FeedService {

    private final FeedRepo feedRepo;
    private final CommentRepo commentRepo;

    @Autowired
    public FeedService(FeedRepo feedRepo, CommentRepo commentRepo) {
        this.feedRepo = feedRepo;
        this.commentRepo = commentRepo;
    }

    /* 게시물 전체 목록 조회 */
    @Transactional(readOnly = true)
    public List<FeedDTO> findByFeeds() {
        List<FeedDTO> feedList = feedRepo.findAll();
        return feedRepo.findAll();
    }

    /* 게시물 상세 조회시 댓글 출력 */
    public FeedDTO findFeedById(int id) {
        FeedDTO feed = feedRepo.findById(id).orElseThrow();
        List<CommentEntity> comments = commentRepo.findByBoardId(feed);
        feed.setComments(comments);

        return feed;
    }

    /* 게시물 생성 */
    @Transactional
    public FeedDTO createFeed(FeedDTO feedDTO, long memberId) {
//        MemberInfoDTO memberInfoDTO = memberInfoRepo.findById(memberId).orElseThrow();
//        feedDTO.setMemberId(memberInfoDTO);
//        return feedRepo.save(feedDTO);feedDTO
        return null;
    }

    /* 게시물 수정 */
    @Transactional
    public FeedDTO modifyFeed(FeedDTO feedDTO) {
        FeedDTO feed = feedRepo.findById(feedDTO.getBoardId()).orElseThrow();
        feed.setBoardTitile(feedDTO.getBoardTitile());
        feed.setBoardContent(feedDTO.getBoardContent());

        return feed;
    }

    /* 게시물 삭제 */
    @Transactional
    public void removeFeed(int boardId) {
        feedRepo.deleteById(boardId);
    }
}
