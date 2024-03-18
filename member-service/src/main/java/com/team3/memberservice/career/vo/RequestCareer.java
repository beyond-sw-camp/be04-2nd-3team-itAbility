package com.team3.memberservice.career.vo;

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

