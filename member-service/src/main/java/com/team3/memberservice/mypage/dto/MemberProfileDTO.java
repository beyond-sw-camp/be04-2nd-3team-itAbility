package com.team3.memberservice.mypage.dto;


import com.team3.memberservice.img.entity.ImageEntity;
import com.team3.memberservice.member.dto.MemberInfoDTO;
import com.team3.memberservice.mypage.entity.DegreeEntity;
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
