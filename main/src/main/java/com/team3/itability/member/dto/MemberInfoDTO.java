package com.team3.itability.member.dto;

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

    public MemberInfoDTO(Long userId, String name, String email, Provider provider) {
        this.memberId = userId;
        this.name = name;
        this.email = email;
        this.provider = provider.name();
    }



    public void update(String name, String phone, String birthdate) {
        this.name=name;
        this.phone=phone;
        this.birthDate=birthdate;
    }

    public void incrementBlacklistCount() {
        this.blacklistCount += 1;
    }

    public void decrementReportCount(int decrementValue) {
        this.mbReportCount -= decrementValue;
        if (this.mbReportCount < 0) {
            this.mbReportCount = 0;
        }
    }
}