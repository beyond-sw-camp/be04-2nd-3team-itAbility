package com.team3.itability.mypage.dto;


import com.team3.itability.member.dto.MemberInfoDTO;
import com.team3.itability.mypage.entity.DegreeDTO;
import com.team3.itability.mypage.entity.ImageDTO;
import jakarta.persistence.*;
import lombok.*;

@Setter
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class MemberProfile {
    private String nickname;
    private MemberInfoDTO memberInfo;
    private ImageDTO img;
    private DegreeDTO degree;


    public MemberProfile(MemberInfoDTO memberInfo, String nickname, ImageDTO img) {
        this.memberInfo = memberInfo;
        this.nickname = nickname;
        this.img = img;
    }
}
