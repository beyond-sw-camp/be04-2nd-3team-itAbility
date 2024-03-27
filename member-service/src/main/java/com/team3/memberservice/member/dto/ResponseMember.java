package com.team3.memberservice.member.dto;

import lombok.Getter;


@Getter
public class ResponseMember {

    private long memberId;

    private String email;

    private String provider;

    private String name;

    private String birthDate;

    private String phone;

    private boolean blacklistStatus;

    private int blacklistCount;

    private int mbReportCount;

    public ResponseMember(MemberInfoDTO member) {
        this.memberId = member.getMemberId();
        this.email = member.getEmail();
        this.provider = member.getRole();
        this.name = member.getName();
        this.birthDate = member.getBirthDate();
        this.phone = member.getPhone();
        this.blacklistStatus = member.isBlacklistStatus();
        this.blacklistCount = member.getBlacklistCount();
        this.mbReportCount = member.getMbReportCount();
    }
}
