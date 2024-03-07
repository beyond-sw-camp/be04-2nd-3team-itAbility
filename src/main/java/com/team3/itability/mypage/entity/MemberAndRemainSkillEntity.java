package com.team3.itability.mypage.entity;

import com.team3.itability.mypage.dto.SkillDTO;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class MemberAndRemainSkillEntity {
    private long memberId;
    private List<SkillDTO> memberSkillList;
    private List<SkillDTO> remainSkillList;
}
