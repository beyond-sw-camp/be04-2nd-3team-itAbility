package com.team3.itability.mypage.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MemberSkill {
    private long memberId;
    private int skillId;
}
