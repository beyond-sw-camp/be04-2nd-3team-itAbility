package com.team3.itability.member.dto;

import com.team3.itability.mypage.dto.MemberProfileDTO;
//import com.team3.itability.mypage.MemberProfileDTO;
import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@ToString(exclude = "memberProfile")
@Entity(name = "member_info_dto")
@Table(name = "member_info")
public class MemberInfoDTO {
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

    // 연관 관계 설정 (MemberProfile)
//    @OneToOne(mappedBy = "memberId", cascade = CascadeType.ALL)
//    private MemberProfileDTO memberProfile;


    public MemberInfoDTO() {
    }

    public MemberInfoDTO(int memberId, String email, String provider, String pwd, String name, String birthDate, String phone, boolean blacklistStatus, int blacklistCount, int mbReportCount) {
        this.memberId = memberId;
        this.email = email;
        this.provider = provider;
        this.pwd = pwd;
        this.name = name;
        this.birthDate = birthDate;
        this.phone = phone;
        this.blacklistStatus = blacklistStatus;
        this.blacklistCount = blacklistCount;
        this.mbReportCount = mbReportCount;
    }


    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isBlacklistStatus() {
        return blacklistStatus;
    }

    public void setBlacklistStatus(boolean blacklistStatus) {
        this.blacklistStatus = blacklistStatus;
    }

    public int getBlacklistCount() {
        return blacklistCount;
    }

    public void setBlacklistCount(int blacklistCount) {
        this.blacklistCount = blacklistCount;
    }

    public int getMbReportCount() {
        return mbReportCount;
    }

    public void setMbReportCount(int mbReportCount) {
        this.mbReportCount = mbReportCount;
    }

    @Override
    public String toString() {
        return "MemberInfoDTO{" +
                "memberId=" + memberId +
                ", email='" + email + '\'' +
                ", provider='" + provider + '\'' +
                ", pwd='" + pwd + '\'' +
                ", name='" + name + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", phone='" + phone + '\'' +
                ", blacklistStatus=" + blacklistStatus +
                ", blacklistCount=" + blacklistCount +
                ", mbReportCount=" + mbReportCount +
                '}';
    }
}