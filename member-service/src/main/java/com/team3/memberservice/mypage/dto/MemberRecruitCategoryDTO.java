package com.team3.memberservice.mypage.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MemberRecruitCategoryDTO {
    private long memberId;
    private int recruitCategoryId;
}
