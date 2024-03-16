package com.team3.memberservice.skill.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Embeddable
@ToString
@EqualsAndHashCode
public class MemberSkillId implements Serializable {

    @Column(name = "member_id")
    private long memberId;

    @Column(name = "skill_id")
    private int skillId;

}