package com.team3.memberservice.skill.dto;

import com.team3.memberservice.skill.entity.MemberSkillId;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ResponseSkillList {
    List<MemberSkillId> skillList;
}
