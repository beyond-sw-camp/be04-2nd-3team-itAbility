package com.team3.itability.mypage.dto;


import com.team3.itability.mypage.entity.MemberProfileDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Career {

    private int careerId;
    private String companyName;
    private String startDate;
    private String endDate;
    private String role;
    private String assignedTask;
    private boolean isCurrentJob;
    private MemberProfileDTO memberId;
}
