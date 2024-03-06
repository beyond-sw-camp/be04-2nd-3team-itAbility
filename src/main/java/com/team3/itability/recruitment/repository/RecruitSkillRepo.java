package com.team3.itability.recruitment.repository;

import com.team3.itability.recruitment.dto.RecruitSkillDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecruitSkillRepo extends JpaRepository<RecruitSkillDTO, Integer> {
}
