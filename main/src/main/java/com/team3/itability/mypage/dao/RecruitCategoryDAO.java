package com.team3.itability.mypage.dao;

import com.team3.itability.recruitment.aggregate.RecruitCategoryDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecruitCategoryDAO extends JpaRepository<RecruitCategoryDTO, Integer> {
}
