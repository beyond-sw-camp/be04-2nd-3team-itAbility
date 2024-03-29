package com.team3.boardservice.member.dto;

import lombok.Data;

@Data
public class ResponseMember2 {

    private long memberId;

    private String email;

    private String provider;

    private String pwd;

    private String name;

    private String birthDate;

    private String phone;

    private boolean blacklistStatus;

    private int blacklistCount;

    private int mbReportCount;
}