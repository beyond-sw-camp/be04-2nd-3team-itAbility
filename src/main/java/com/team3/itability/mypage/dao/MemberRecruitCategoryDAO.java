package com.team3.itability.mypage.dao;

import com.team3.itability.mypage.entity.MemberRecruitCategoryDTO;
import com.team3.itability.mypage.entity.MemberRecruitCategoryId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRecruitCategoryDAO extends JpaRepository<MemberRecruitCategoryDTO, MemberRecruitCategoryId> {
    List<MemberRecruitCategoryDTO> findByIdMemberId(Long memberId);
}
