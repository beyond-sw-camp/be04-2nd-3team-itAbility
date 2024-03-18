package com.team3.memberservice.career.dao;

import com.team3.memberservice.mypage.entity.CareerEntity;
import com.team3.memberservice.mypage.entity.MemberProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CareerDAO extends JpaRepository<CareerEntity,Integer> {

    List<CareerEntity> findByMemberId(MemberProfileEntity member);

    List<CareerEntity> findByMemberIdMemberId(long memberId);

    List<CareerEntity> findByMemberIdMemberInfoMemberId(long memberId);
}
