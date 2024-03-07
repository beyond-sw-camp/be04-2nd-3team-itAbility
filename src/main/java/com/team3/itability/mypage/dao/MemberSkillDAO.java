package com.team3.itability.mypage.dao;

import com.team3.itability.mypage.entity.MemberSkillDTO;
import com.team3.itability.mypage.entity.MemberSkillId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberSkillDAO extends JpaRepository<MemberSkillDTO, MemberSkillId> {
    List<MemberSkillDTO> findByIdMemberId(Long memberId);

}
