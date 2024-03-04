package com.team3.itability.snsapi.kakao.domain;

import com.team3.itability.member.dto.Provider;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "kakao_user")
public class Kakaouser {

    @Id
    private Long userId;
    private String imgId;
    private String email;
    private String name;
    @Enumerated(EnumType.STRING)
    private Provider provider;
//    private String birthYear; // java,time localdate localtime
//    private String birthDay;
//    private String phone;
//    private String gender;

}
