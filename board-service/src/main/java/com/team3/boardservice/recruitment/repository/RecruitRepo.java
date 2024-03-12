package com.team3.boardservice.recruitment.repository;

import com.team3.boardservice.recruitment.aggregate.RecruitDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecruitRepo extends JpaRepository<RecruitDTO, Integer> {

}
