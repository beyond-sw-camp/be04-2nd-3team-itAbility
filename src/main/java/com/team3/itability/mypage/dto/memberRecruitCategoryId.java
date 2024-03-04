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
public class memberRecruitCategoryId implements Serializable {

    private Integer memberId;
    private Integer recruitCategoryId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof memberRecruitCategoryId that)) return false;
        return Objects.equals(memberId, that.memberId) && Objects.equals(recruitCategoryId, that.recruitCategoryId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(memberId, recruitCategoryId);
    }
}
