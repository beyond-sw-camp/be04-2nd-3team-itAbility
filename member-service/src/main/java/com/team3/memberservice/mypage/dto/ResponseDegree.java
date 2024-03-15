package com.team3.memberservice.mypage.dto;


import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ResponseDegree {
    MemberProfileDTO profile;
    DegreeDTO degree;
}
