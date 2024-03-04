package com.team3.itability.mypage.dto;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Embeddable
@ToString
public class MemberSkillId implements Serializable {

    private Integer memberId;
    private Integer skillId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MemberSkillId that)) return false;
        return Objects.equals(getMemberId(), that.getMemberId()) && Objects.equals(getSkillId(), that.getSkillId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMemberId(), getSkillId());
    }
}