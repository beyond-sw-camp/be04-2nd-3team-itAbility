package com.team3.itability.mypage.dto;

import com.team3.itability.mypage.entity.SkillEntity;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class MemberAndRemainSkillDTO {
    private long memberId;
    private List<SkillEntity> memberSkillList;
    private List<SkillEntity> remainSkillList;
}
