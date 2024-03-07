package com.team3.itability.mypage.dao;


import com.team3.itability.mypage.entity.MemberProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MemberProfileDAO extends JpaRepository<MemberProfileEntity,Long> {

}
