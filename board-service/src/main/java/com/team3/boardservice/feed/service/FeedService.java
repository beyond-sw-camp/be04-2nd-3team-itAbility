package com.team3.boardservice.feed.service;

import com.team3.boardservice.client.MemberServerClient;
import com.team3.boardservice.feed.dto.FeedDTO;

import com.team3.boardservice.feed.dto.ImgDTO;
import com.team3.boardservice.feed.dto.LikeDTO;
import com.team3.boardservice.feed.repository.FeedRepo;
import com.team3.boardservice.feed.repository.ImgRepo;
import com.team3.boardservice.feed.repository.LikeRepo;
import com.team3.boardservice.feed.vo.FeedVO;
import com.team3.boardservice.feed.vo.LikeVO;
import com.team3.boardservice.feed.vo.ResponseFeedVO;
import com.team3.boardservice.reple.aggregate.CommentEntity;
import com.team3.boardservice.reple.dto.CommentDTO;
import com.team3.boardservice.reple.repository.CommentRepo;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Service
public class FeedService {

    private final FeedRepo feedRepo;
    private final CommentRepo commentRepo;
    private final ModelMapper modelMapper;
    private final ImgService imgService;
    private final ImgRepo imgRepo;
    private final LikeRepo likeRepo;

    @Autowired
    public FeedService(FeedRepo feedRepo, CommentRepo commentRepo, ModelMapper modelMapper, ImgService imgService, ImgRepo imgRepo,
                       LikeRepo likeRepo) {
        this.feedRepo = feedRepo;
        this.commentRepo = commentRepo;
        this.modelMapper = modelMapper;
        this.imgService = imgService;
        this.imgRepo = imgRepo;
        this.likeRepo = likeRepo;
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

    /* 게시물 생성 -fin */
    @Transactional
    public FeedVO createFeed(FeedDTO feedDTO,
                             long memberId
//                             ,List<ImgDTO> imgDTOS
                             ) {
        feedDTO.setMemberId(memberId);
//        feedDTO.setImgId(imgDTOS);
        FeedDTO saveFeed = feedRepo.save(feedDTO);
        return modelMapper.map(saveFeed,FeedVO.class);
    }

    /* 게시물 수정 */
    @Transactional
    public FeedVO modifyFeed(int boardId,
                             FeedDTO feedDTO
//                             ,List<ImgDTO> addedImgs,
//                             List<String> removeImgIds
                             ) {
        FeedDTO feed = feedRepo.findById(feedDTO.getBoardId()).orElseThrow();
        feed.setBoardTitle(feedDTO.getBoardTitle());
        feed.setBoardContent(feedDTO.getBoardContent());

//        // 게시물 수정시 새로운 이미지 추가
//        if (addedImgs != null && !addedImgs.isEmpty()) {
//            for (ImgDTO imgDTO : addedImgs) {
//                feed.getImgId().add(imgDTO);
//                imgRepo.save(imgDTO);
//            }
//        }
//        // 게시물 수정시 삭제할 이미지 처리
//        if (removeImgIds != null && !removeImgIds.isEmpty()) {
//            for (String imgId : removeImgIds) {
//                feed.getImgId().removeIf(img -> img.getImgId().equals(imgId));
//                imgRepo.deleteById(imgId);
//            }
//        }
        feedRepo.save(feed);
        return modelMapper.map(feed,FeedVO.class);
    }

    /* 게시물 삭제 */
    @Transactional
    public void removeFeed(int boardId) {
        System.out.println("boardId = " + boardId);
        FeedDTO deleteFeed = feedRepo.findById(boardId).orElseThrow();
        likeRepo.deleteAllByBoardIdBoardId(boardId);
        commentRepo.deleteAllByBoardIdBoardId(boardId);
        List<ImgDTO> imgDTOList = deleteFeed.getImgId();

        // 생성 되었던 이미지 DB에서 삭제
        if (imgDTOList != null && !imgDTOList.isEmpty()) {
            for (ImgDTO img : imgDTOList) {
                imgRepo.deleteById(img.getImgId());
            }
        }
        feedRepo.deleteById(boardId);
    }


    public List<ResponseFeedVO> findMemberFeeds(long memberId) {
        List<FeedDTO> feedDTOS = feedRepo.findByMemberId(memberId);
        List<ResponseFeedVO> feeds = new ArrayList<>();

        feedDTOS.forEach(feed ->{
            List<LikeDTO> likes = likeRepo.findByBoardIdBoardId(feed.getBoardId());
            List<CommentEntity> comments = commentRepo.findByBoardId(feed);
            feeds.add(new ResponseFeedVO(feed,likes.size(),comments));
        } );
        return feeds;
    }
}
