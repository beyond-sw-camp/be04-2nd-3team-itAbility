package com.team3.itability.recruitment.dto;

import com.team3.itability.mypage.entity.SkillDTO;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity(name = "recruit_skill")
@Table(name = "recruit_skill")
public class RecruitSkillDTO {
    @EmbeddedId
    private RecruitSkillId id;

    @MapsId("recruitId")
    @ManyToOne
    @JoinColumn(name="recruit_id")
    private RecruitDTO recruitId;

    @MapsId("skillId")
    @ManyToOne
    @JoinColumn(name="skill_id")
    private SkillDTO skillDTO;
}
