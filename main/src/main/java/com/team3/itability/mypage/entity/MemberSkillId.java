package com.team3.itability.mypage.entity;
import jakarta.persistence.*;
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