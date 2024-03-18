package com.team3.memberservice.career.dto;


import com.team3.memberservice.mypage.entity.MemberProfileEntity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CareerDTO {

    private int careerId;
    private String companyName;
    private String startDate;
    private String endDate;
    private String role;
    private String assignedTask;
    private boolean isCurrentJob;
    private MemberProfileEntity memberId;
}
