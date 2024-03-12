package com.team3.itability.recruitment.repository;

import com.team3.itability.recruitment.aggregate.RecruitSkillDTO;
import com.team3.itability.recruitment.aggregate.RecruitSkillId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RecruitSkillRepo extends JpaRepository<RecruitSkillDTO, Integer> {
    Optional<RecruitSkillDTO> findById(RecruitSkillId recruitSkillId);
}
