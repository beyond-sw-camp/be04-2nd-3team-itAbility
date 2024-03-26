package com.team3.boardservice.recruitment.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class RecruitSkillVO {

    int recruitId;

    int skillId;

    String skillName;
}
