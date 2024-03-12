package com.team3.reportservice.report.dto;
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
