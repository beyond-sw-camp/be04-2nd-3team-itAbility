package com.team3.itability.recruitment.dao;

import com.team3.itability.recruitment.dto.RecruitCategoryDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecruitCateRepo extends JpaRepository<RecruitCategoryDTO, Integer> {
}
