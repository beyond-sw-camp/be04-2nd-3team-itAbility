package com.team3.itability.mypage.entity;

import com.team3.itability.img.entity.ImageEntity;
import com.team3.itability.member.dto.MemberInfoDTO;
import jakarta.persistence.*;
import lombok.*;


@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="member_profile_dto")
@Table(name="member_profile")
@Getter
@ToString
public class MemberProfileEntity {
    @Id
    @Column(name = "member_id")
    private long memberId;
    @Column(name = "nickname")
    private String nickname;


    // 연관 관계 설정 (MemberInfo)
    @OneToOne
    @MapsId
    @JoinColumn(name = "member_id")
    private MemberInfoDTO memberInfo;

    @OneToOne
    @JoinColumn(name = "img_id")
    private ImageEntity img;

    @ManyToOne
    @JoinColumn(name = "Degree_id")
    private DegreeEntity degree;


    public MemberProfileEntity(MemberInfoDTO memberInfo, String nickname, ImageEntity img) {
        this.memberInfo = memberInfo;
        this.nickname = nickname;
        this.img = img;
    }
}
