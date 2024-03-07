package com.team3.itability.mypage.dto;

import jakarta.persistence.Column;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MemberRecruitCategory {
    private long memberId;
    private int recruitCategoryId;
}
