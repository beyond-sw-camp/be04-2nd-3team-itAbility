package com.team3.itability.mypage.dto;


import com.team3.itability.member.dto.MemberInfoDTO;
import com.team3.itability.mypage.entity.DegreeEntity;
import com.team3.itability.img.entity.ImageEntity;
import lombok.*;

@Setter
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class MemberProfileDTO {
    private String nickname;
    private MemberInfoDTO memberInfo;
    private ImageEntity img;
    private DegreeEntity degree;


    public MemberProfileDTO(MemberInfoDTO memberInfo, String nickname, ImageEntity img) {
        this.memberInfo = memberInfo;
        this.nickname = nickname;
        this.img = img;
    }
}
