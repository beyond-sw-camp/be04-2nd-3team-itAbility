package com.team3.boardservice.recruitment.repository;

import com.team3.boardservice.recruitment.aggregate.RecruitSkillDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecruitSkillRepo extends JpaRepository<RecruitSkillDTO, Integer> {

    RecruitSkillDTO findByIdRecruitId(int recruitId);
}
