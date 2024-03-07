package com.team3.itability.mypage.dto;

import com.team3.itability.mypage.entity.MemberProfileDTO;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Degree {
    private int degreeId;
    private String finalEduName;
    private String enrollDate;
    private String graduateDate;
    private String major;
    private boolean registerStatus;
}
