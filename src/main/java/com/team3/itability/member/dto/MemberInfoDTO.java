package com.team3.itability.member.dto;

import com.team3.itability.mypage.dto.MemberProfileDTO;
//import com.team3.itability.mypage.MemberProfileDTO;
import jakarta.persistence.*;
import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(exclude = "memberProfile")
@Entity(name = "member_info_dto")
@Table(name = "member_info")
public class MemberInfoDTO {
    @Id
    @Column(name = "member_id")
    private long memberId;

    @Column(name = "email")

    private String email;
    @Column(name = "provider")

    private String provider;

    @Column(name = "pwd")
    private String pwd;

    @Column(name = "name")
    private String name;

    @Column(name = "birthdate")
    private String birthDate;

    @Column(name = "phone")
    private String phone;

    @Column(name = "is_blacklisted")
    private boolean blacklistStatus;

    @Column(name = "blacklist_count")
    private int blacklistCount;

    @Column(name = "report_count")
    private int mbReportCount;

    // 연관 관계 설정 (MemberProfile)
//    @OneToOne(mappedBy = "memberId", cascade = CascadeType.ALL)
//    private MemberProfileDTO memberProfile;

}