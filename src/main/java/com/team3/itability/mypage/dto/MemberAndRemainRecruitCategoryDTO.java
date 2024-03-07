package com.team3.itability.mypage.dto;


import com.team3.itability.recruitment.aggregate.RecruitCategoryDTO;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class MemberAndRemainRecruitCategoryDTO {
    private long memberId;
    private List<RecruitCategoryDTO> memberRecruitList;
    private List<RecruitCategoryDTO> remainRecruitList;

}
