package com.team3.boardservice.recruitment.aggregate;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(exclude = "recruit")
@Entity
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
