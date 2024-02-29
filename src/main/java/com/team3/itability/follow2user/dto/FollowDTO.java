package com.team3.itability.follow2user.dto;


import com.team3.itability.member.dto.MemberInfoDTO;
import com.team3.itability.mypage.MemberProfileDTO;
import jakarta.persistence.*;
import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

@Entity(name="follow_dto")
@Table(name="follow")
public class FollowDTO {

    @Id
    @Column(name="follow_id")
    private int followId;

    @JoinColumn(name="member_id_following")
    @ManyToOne
    private MemberProfileDTO following;

    @JoinColumn(name="member_id_followed")
    @ManyToOne
    private MemberProfileDTO followed;

}
