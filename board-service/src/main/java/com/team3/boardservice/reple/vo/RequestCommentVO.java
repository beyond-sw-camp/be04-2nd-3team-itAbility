package com.team3.boardservice.reple.vo;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class RequestCommentVO {
    private int cmtId;
    private int writeDate;
    private int reportCount;
    private int boardId;
    private long memberId;
    private String cmtContent;

}
