package com.team3.itability.mypage.dao;

import com.team3.itability.mypage.dto.SkillDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillDAO extends JpaRepository<SkillDTO,Integer> {

}
