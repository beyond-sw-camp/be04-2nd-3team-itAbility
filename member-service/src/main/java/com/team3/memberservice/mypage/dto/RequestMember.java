package com.team3.memberservice.mypage.dto;


import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
@ToString
public class RequestMember {
    private String name;
    private String nickname;
    private String phone;
    private String birthDate;
}
