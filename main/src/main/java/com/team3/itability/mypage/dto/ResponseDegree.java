package com.team3.itability.mypage.dto;


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
