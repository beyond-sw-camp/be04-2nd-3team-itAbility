package com.team3.itability.recruitment.repository;

import com.team3.itability.recruitment.aggregate.RefRecruitCategoryDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefRecruitRepo extends JpaRepository<RefRecruitCategoryDTO, Integer> {
    void deleteByIdRecruitId(Integer id_recruitId);
}
