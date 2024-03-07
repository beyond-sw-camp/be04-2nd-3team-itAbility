package com.team3.itability.follow2user.dto;


import com.team3.itability.member.dto.MemberInfoDTO;
import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class FollowDTO {
    private int followId;

    private MemberInfoDTO following;

    private MemberInfoDTO followed;
    public String getfollowing_nickname() {
        return following != null ? following.getName() : null;
    }

    public String getfollowed_nickname() {
        return followed != null ? followed.getName() : null;
    }

}
