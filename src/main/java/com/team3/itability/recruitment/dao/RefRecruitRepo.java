package com.team3.itability.recruitment.dao;

import com.team3.itability.recruitment.dto.RefRecruitCategoryDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefRecruitRepo extends JpaRepository<RefRecruitCategoryDTO, Integer> {
}
