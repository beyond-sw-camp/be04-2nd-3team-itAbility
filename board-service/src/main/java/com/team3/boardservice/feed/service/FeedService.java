package com.team3.boardservice.feed.service;

import com.team3.boardservice.feed.dto.FeedDTO;

import com.team3.boardservice.feed.repository.FeedRepo;
import com.team3.boardservice.feed.vo.FeedVO;
import com.team3.boardservice.reple.aggregate.CommentEntity;
import com.team3.boardservice.reple.dto.CommentDTO;
import com.team3.boardservice.reple.repository.CommentRepo;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
public class FeedService {

    private final FeedRepo feedRepo;
    private final CommentRepo commentRepo;
    private final ModelMapper modelMapper;

    @Autowired
    public FeedService(FeedRepo feedRepo, CommentRepo commentRepo, ModelMapper modelMapper) {
        this.feedRepo = feedRepo;
        this.commentRepo = commentRepo;
        this.modelMapper = modelMapper;
    }

    /* 게시물 전체 목록 조회 */
    @Transactional(readOnly = true)
    public List<FeedDTO> findByFeeds() {
        List<FeedDTO> feedList = feedRepo.findAll();
        return feedRepo.findAll();
    }

    /* 게시물 상세 조회시 댓글 출력 */
    public FeedVO findFeedById(int id) {
        FeedDTO feed = feedRepo.findById(id).orElseThrow();
        List<CommentEntity> comments = commentRepo.findByBoardId(feed);
        feed.setComments(comments);
        List<CommentDTO> commentDTOS = new ArrayList<>();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        comments.forEach(commentEntity -> commentDTOS.add(modelMapper.map(commentEntity, CommentDTO.class)));
        FeedVO feedVO = modelMapper.map(feed,FeedVO.class);

        return feedVO;
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
