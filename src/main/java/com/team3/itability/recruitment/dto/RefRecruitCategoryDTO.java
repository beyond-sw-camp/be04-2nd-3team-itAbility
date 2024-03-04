package com.team3.itability.recruitment.dto;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(exclude = "recruit")
@Entity(name = "ref_recruit_category")
@Table(name = "ref_recruit_category")
public class RefRecruitCategoryDTO {
    @EmbeddedId
    private RefRecruitCategoryId id;

    @MapsId("recruitId")
    @ManyToOne
    @JoinColumn(name = "recruit_id")
    private RecruitDTO recruit;

    @MapsId("recruitCategoryId")
    @ManyToOne
    @JoinColumn(name = "recruit_category_id")
    private RecruitCategoryDTO recruitCategory;


}
