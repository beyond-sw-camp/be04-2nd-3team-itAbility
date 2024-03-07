package com.team3.itability.reple.dto;


import com.team3.itability.feed.dto.FeedDTO;
import com.team3.itability.member.dto.MemberInfoDTO;
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
    private FeedDTO boardId;
    private MemberInfoDTO memberId;
    private String cmtContent;


}
