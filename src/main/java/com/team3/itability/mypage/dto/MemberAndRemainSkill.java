package com.team3.itability.mypage.dto;

import com.team3.itability.mypage.entity.SkillDTO;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class MemberAndRemainSkill {
    private long memberId;
    private List<SkillDTO> memberSkillList;
    private List<SkillDTO> remainSkillList;
}
