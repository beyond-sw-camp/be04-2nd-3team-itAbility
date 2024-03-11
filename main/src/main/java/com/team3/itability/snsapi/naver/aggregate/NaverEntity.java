package com.team3.itability.snsapi.naver.aggregate;

import com.team3.itability.member.dto.Provider;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "naver_user")
public class NaverEntity {
    @Id
    private String userId;
    private String imgId;
    private String email;
    private String name;
    @Enumerated(EnumType.STRING)
    private Provider provider;
}
