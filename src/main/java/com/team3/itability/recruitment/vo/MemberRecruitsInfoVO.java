package com.team3.itability.recruitment.vo;

import com.team3.itability.recruitment.aggregate.RecruitStatus;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MemberRecruitsInfoVO {

    private int memberRecruitInfoId;

    private int recruitId;

    private long memberId;

    private RecruitStatus recruitStatus;
}
