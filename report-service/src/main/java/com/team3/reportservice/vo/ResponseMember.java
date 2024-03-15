package com.team3.reportservice.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.ToString;


@Getter
@ToString
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
