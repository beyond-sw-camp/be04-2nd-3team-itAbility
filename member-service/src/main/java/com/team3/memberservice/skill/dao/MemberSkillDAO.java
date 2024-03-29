package com.team3.memberservice.skill.dao;


import com.team3.memberservice.skill.entity.MemberSkillEntity;
import com.team3.memberservice.skill.entity.MemberSkillId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberSkillDAO extends JpaRepository<MemberSkillEntity, MemberSkillId> {
    List<MemberSkillEntity> findByIdMemberId(Long memberId);

    void deleteAllByIdMemberId(long memberId);
}
