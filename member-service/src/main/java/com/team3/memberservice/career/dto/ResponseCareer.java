package com.team3.memberservice.career.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseCareer {
    private int careerId;
    private String companyName;
    private String startDate;
    private String endDate;
    private String role;
    private String assignedTask;
    private boolean isCurrentJob;
}
