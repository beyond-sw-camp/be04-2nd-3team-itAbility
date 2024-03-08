package com.team3.itability.feed.service;

import com.team3.itability.reple.aggregate.CommentEntity;
import com.team3.itability.reple.repository.CommentRepo;
import com.team3.itability.feed.repository.FeedRepo;
import com.team3.itability.feed.dto.FeedDTO;
import com.team3.itability.member.dao.MemberInfoRepo;
import com.team3.itability.member.dto.MemberInfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class FeedService {

    @Autowired
    private FeedRepo feedRepo;

    @Autowired
    private MemberInfoRepo memberInfoRepo;

    @Autowired
    private CommentRepo commentRepo;


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
        MemberInfoDTO memberInfoDTO = memberInfoRepo.findById(memberId).orElseThrow();
        feedDTO.setMemberId(memberInfoDTO);
        return feedRepo.save(feedDTO);
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
