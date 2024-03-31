package com.team3.boardservice.mypage.dao;


import com.team3.boardservice.mypage.entity.MemberRecruitCategoryEntity;
import com.team3.boardservice.mypage.entity.MemberRecruitCategoryId;
import com.team3.boardservice.recruitment.aggregate.MemberRecruitsInfoDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRecruitCategoryDAO extends JpaRepository<MemberRecruitCategoryEntity, MemberRecruitCategoryId> {
    void deleteAllByIdMemberId(long memberId);

    List<MemberRecruitCategoryEntity> findByIdMemberId(Long memberId);
}
