package com.team3.boardservice.reple.dto;



import com.team3.boardservice.feed.dto.FeedDTO;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CommentDTO {

    private int cmtId;
    private String writeDate;
    private int reportCount;
    private long memberId;
    private String cmtContent;
}
