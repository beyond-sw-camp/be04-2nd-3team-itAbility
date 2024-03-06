package com.team3.itability.mypage.dto;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Embeddable
@ToString
@EqualsAndHashCode
public class MemberSkillId implements Serializable {

//    @ManyToOne(fetch = FetchType.LAZY)
    @Column(name = "member_id")
    private long memberId;

    @Column(name = "skill_id")
//    @ManyToOne(fetch = FetchType.LAZY)
    private int skillId;

}