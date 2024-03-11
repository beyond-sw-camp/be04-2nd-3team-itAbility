package com.team3.itability.recruitment.vo;

import com.team3.itability.recruitment.aggregate.RecruitType;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class RecruitVO {

    private int RecruitId;

    private RecruitType recruitType;

    private String recruitTitle;

    private String recruitExpDate;

    private Integer recruitMbCnt;

    private String recruitName;

    private Long memberId;
}
