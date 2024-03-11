package com.team3.itability.recruitment.aggregate;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Embeddable
public class RefRecruitCategoryId implements Serializable {

    private Integer recruitId;

    private Integer recruitCategoryId;
}