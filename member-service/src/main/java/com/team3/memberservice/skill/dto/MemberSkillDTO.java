package com.team3.memberservice.skill.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MemberSkillDTO {
    private long memberId;
    private int skillId;
}
