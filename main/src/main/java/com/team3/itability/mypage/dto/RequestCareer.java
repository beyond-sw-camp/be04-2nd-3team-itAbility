package com.team3.itability.mypage.dto;

import com.team3.itability.mypage.entity.MemberProfileEntity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RequestCareer {
    private String companyName;
    private String startDate;
    private String endDate;
    private String role;
    private String assignedTask;
    private boolean isCurrentJob;
}

