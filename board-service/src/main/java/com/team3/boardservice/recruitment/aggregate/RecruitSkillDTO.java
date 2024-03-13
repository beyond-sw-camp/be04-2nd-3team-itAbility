package com.team3.boardservice.recruitment.aggregate;


import com.team3.boardservice.mypage.entity.SkillEntity;
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
    private SkillEntity skillEntity;
}
