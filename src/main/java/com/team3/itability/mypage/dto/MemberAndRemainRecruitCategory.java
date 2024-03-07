package com.team3.itability.mypage.dto;

import com.team3.itability.recruitment.dto.RecruitCategoryDTO;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class MemberAndRemainRecruitCategory {
    private long memberId;
    private List<RecruitCategoryDTO> memberRecruitList;
    private List<RecruitCategoryDTO> remainRecruitList;

}
