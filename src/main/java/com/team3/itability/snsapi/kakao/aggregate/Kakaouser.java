package com.team3.itability.snsapi.kakao.aggregate;

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
}
