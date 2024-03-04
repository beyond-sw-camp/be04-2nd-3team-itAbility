package com.team3.itability.mypage.dao;

import com.team3.itability.mypage.dto.MemberSkillDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberSkillDAO extends JpaRepository<MemberSkillDTO,Long> {

}
