package com.team3.itability.recruitment.dto;
import java.io.Serializable;
import java.util.Objects;
import jakarta.persistence.Embeddable;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Embeddable
public class RefRecruitCategoryId implements Serializable {
    private Integer recruitId; // RecruitDTO의 식별자와 매칭
    private Integer recruitCategoryId; // RecruitCategoryDTO의 식별자와 매칭
}