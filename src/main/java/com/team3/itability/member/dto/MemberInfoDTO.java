package com.team3.itability.member.dto;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity(name = "member_info_dto")
@Table(name = "member_info")
public class MemberInfoDTO {
    //  identity 하여 오토인크리면트 사용
    @Id
    @Column(name = "member_id")
    private int memberId;

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
}
