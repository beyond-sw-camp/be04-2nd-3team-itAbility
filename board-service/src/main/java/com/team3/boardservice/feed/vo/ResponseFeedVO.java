package com.team3.boardservice.feed.vo;

import com.team3.boardservice.feed.dto.FeedDTO;
import com.team3.boardservice.feed.dto.ImgDTO;
import com.team3.boardservice.reple.aggregate.CommentEntity;
import com.team3.boardservice.reple.dto.CommentDTO;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ResponseFeedVO {
    private int boardId;
    private String boardTitle;
    private String boardContent;
    private String writeDate;
    private int hits;
    private int reportCount;
    private int isActive;
    private long memberId;
    private List<String> imgId;
    private List<CommentEntity> comments;
    private int like;

    public ResponseFeedVO(FeedDTO feed, int likeCnt, List<CommentEntity> comments) {
        this.boardId = feed.getBoardId();
        this.boardTitle = feed.getBoardTitle();
        this.boardContent = feed.getBoardContent();
        this.writeDate = feed.getWriteDate();
        this.hits = feed.getHits();
        this.reportCount = feed.getReportCount();
        this.isActive = feed.getIsActive();
        imgId= new ArrayList<>();
        feed.getImgId().forEach(imgDTO ->{
            imgId.add(imgDTO.getPath());
        });
        this.comments = comments;
        this.like = likeCnt;
    }
}
