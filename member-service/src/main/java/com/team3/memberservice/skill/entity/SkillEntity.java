package com.team3.memberservice.skill.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity(name = "skill_dto")
@Table(name = "skill")
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SkillEntity {
    @Id
    @Column(name = "skill_id")
    private int skillId;
    @Column(name = "skill_name")
    private String skillName;
}

