package com.team3.itability.mypage.dao;

import com.team3.itability.mypage.entity.MemberRecruitCategoryEntity;
import com.team3.itability.mypage.entity.MemberRecruitCategoryId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRecruitCategoryDAO extends JpaRepository<MemberRecruitCategoryEntity, MemberRecruitCategoryId> {
    List<MemberRecruitCategoryEntity> findByIdMemberId(Long memberId);
}
