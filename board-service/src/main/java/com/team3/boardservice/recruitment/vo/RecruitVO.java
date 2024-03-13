package com.team3.boardservice.recruitment.vo;

import com.team3.boardservice.recruitment.aggregate.RecruitType;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class RecruitVO {

    private int recruitId;

    private RecruitType recruitType;

    private String recruitTitle;

    private String recruitContent;

    private String recruitExpDate;

    private Integer recruitMbCnt;

    private Long memberId;

    private int skillId;

    private int recruitCategoryId;

}


