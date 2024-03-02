package com.team3.itability.mypage.dto;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Embeddable
public class MemberSkillId implements Serializable {

    private Integer memberId;
    private Integer skillId;

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public Integer getSkillId() {
        return skillId;
    }

    public void setSkillId(Integer skillId) {
        this.skillId = skillId;
    }

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