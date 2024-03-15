package com.team3.boardservice.member.dto;

import lombok.*;

@Getter
public class ResponseMember {

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