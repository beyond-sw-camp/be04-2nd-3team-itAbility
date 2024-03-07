package com.team3.itability.report.dto;

import com.team3.itability.feed.dto.FeedDTO;
import com.team3.itability.member.dto.MemberInfoDTO;


import com.team3.itability.reple.aggregate.CommentEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString


public class ReportDTO {

    private int reportId;
    private Date reportDate = new Date();
    private int reportCategoryId;
    private ReportTargetType reportTargetType;
    private Long memberId;
    private Long reportTargetId;

}
