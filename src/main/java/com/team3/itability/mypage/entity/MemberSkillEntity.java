package com.team3.itability.mypage.entity;

import com.team3.itability.mypage.dto.SkillDTO;
import jakarta.persistence.Entity;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class MemberSkillEntity {
    private long memberId;
    private List<SkillDTO> skill;
}
