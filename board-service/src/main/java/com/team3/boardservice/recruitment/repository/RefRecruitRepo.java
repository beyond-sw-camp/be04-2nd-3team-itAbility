package com.team3.boardservice.recruitment.repository;

import com.team3.boardservice.recruitment.aggregate.RefRecruitCategoryDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefRecruitRepo extends JpaRepository<RefRecruitCategoryDTO, Integer> {

    RefRecruitCategoryDTO findByIdRecruitId(int recruitId);
}
