package com.team3.itability.mypage.entity;

import com.team3.itability.mypage.dto.MemberRecruitCategoryDTO;
import com.team3.itability.mypage.dto.MemberSkillDTO;
import com.team3.itability.recruitment.dto.RecruitCategoryDTO;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class MemberAndRemainRecruitCategoryEntity {
    private long memberId;
    private List<RecruitCategoryDTO> memberRecruitList;
    private List<RecruitCategoryDTO> remainRecruitList;

}
