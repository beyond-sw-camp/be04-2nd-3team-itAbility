package com.team3.itability.mypage.dao;

import com.team3.itability.mypage.entity.CareerEntity;
import com.team3.itability.mypage.entity.MemberProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CareerDAO extends JpaRepository<CareerEntity,Integer> {

    List<CareerEntity> findByMemberId(MemberProfileEntity member);

}
