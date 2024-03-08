package com.team3.itability.mypage.dto;

import com.team3.itability.mypage.entity.MemberSkillId;
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
