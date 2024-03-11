package com.team3.itability.snsapi.google.aggregate;

import com.team3.itability.member.dto.Provider;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "google_user")
public class GoogleEntity {
    @Id
    private Long userId;
    private String imgId;
    private String email;
    private String name;
    @Enumerated(EnumType.STRING)
    private Provider provider;

}
