package com.team3.itability.recruitment.repository;

import com.team3.itability.recruitment.aggregate.RefRecruitCategoryDTO;
import com.team3.itability.recruitment.aggregate.RefRecruitCategoryId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefRecruitRepo extends JpaRepository<RefRecruitCategoryDTO, Integer> {
    void deleteByIdRecruitId(Integer id_recruitId);
    Optional<RefRecruitCategoryDTO> findById(RefRecruitCategoryId refRecruitCategoryId);
}
