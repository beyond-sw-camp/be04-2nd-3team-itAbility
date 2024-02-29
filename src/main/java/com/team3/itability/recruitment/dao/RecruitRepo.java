package com.team3.itability.recruitment.dao;

import com.team3.itability.recruitment.dto.RecruitDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecruitRepo extends JpaRepository<RecruitDTO, Integer> {

}
