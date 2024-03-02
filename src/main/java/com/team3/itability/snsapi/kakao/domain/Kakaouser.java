package com.team3.itability.snsapi.kakao.domain;

import com.team3.itability.member.dto.Provider;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
public class Kakaouser {

    @Id
    private Long userId;
    private String imgId;
    @Enumerated(EnumType.STRING)
    private Provider provider;
    private String name;
    private String birthYear; // java,time localdate localtime
    private String birthDay;
    private String email;
    private String phone;
    private String gender;

    public Kakaouser() {
    }

    public Kakaouser(Long userId, String imgId, Provider provider, String name, String email) {
        this.userId = userId;
        this.imgId = imgId;
        this.provider = provider;
        this.name = name;
//        this.birthYear = birthYear;
//        this.birthDay = birthDay;
        this.email = email;
//        this.phone = phone;
//        this.gender = gender;
    }
}
