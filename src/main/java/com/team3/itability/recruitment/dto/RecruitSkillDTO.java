package com.team3.itability.recruitment.dto;

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
    @Id
    @JoinColumn(name="recruit_id")
    @ManyToOne
    private RecruitDTO recruitId;

//    @JoinColumn(name="skill_id")
//    @ManyToOne
//    private Skill skillDTO;
}
