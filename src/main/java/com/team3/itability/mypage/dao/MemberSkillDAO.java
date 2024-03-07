package com.team3.itability.mypage.dao;

import com.team3.itability.mypage.entity.MemberSkillEntity;
import com.team3.itability.mypage.entity.MemberSkillId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberSkillDAO extends JpaRepository<MemberSkillEntity, MemberSkillId> {
    List<MemberSkillEntity> findByIdMemberId(Long memberId);

}
