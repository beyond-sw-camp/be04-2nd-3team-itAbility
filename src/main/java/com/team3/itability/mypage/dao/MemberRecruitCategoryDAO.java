package com.team3.itability.mypage.dao;

import com.team3.itability.mypage.dto.MemberRecruitCategoryDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRecruitCategoryDAO extends JpaRepository<MemberRecruitCategoryDTO,Long> {
    List<MemberRecruitCategoryDTO> findByIdMemberId(Long memberId);
}
