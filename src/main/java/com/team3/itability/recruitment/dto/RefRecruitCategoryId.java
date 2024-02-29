package com.team3.itability.recruitment.dto;
import java.io.Serializable;
import java.util.Objects;
import jakarta.persistence.Embeddable;

@Embeddable
public class RefRecruitCategoryId implements Serializable {
    private Integer recruitId; // RecruitDTO의 식별자와 매칭
    private Integer recruitCategoryId; // RecruitCategoryDTO의 식별자와 매칭

    public RefRecruitCategoryId() {
    }

    public RefRecruitCategoryId(Integer recruitId, Integer recruitCategoryId) {
        this.recruitId = recruitId;
        this.recruitCategoryId = recruitCategoryId;
    }

    // Getters and Setters
    public Integer getRecruitId() {
        return recruitId;
    }

    public void setRecruitId(Integer recruitId) {
        this.recruitId = recruitId;
    }

    public Integer getRecruitCategoryId() {
        return recruitCategoryId;
    }

    public void setRecruitCategoryId(Integer recruitCategoryId) {
        this.recruitCategoryId = recruitCategoryId;
    }

    // equals() and hashCode()
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RefRecruitCategoryId)) return false;
        RefRecruitCategoryId that = (RefRecruitCategoryId) o;
        return Objects.equals(getRecruitId(), that.getRecruitId()) &&
                Objects.equals(getRecruitCategoryId(), that.getRecruitCategoryId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRecruitId(), getRecruitCategoryId());
    }
}