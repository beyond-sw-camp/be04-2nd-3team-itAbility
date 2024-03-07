package com.team3.itability.mypage.dao;


import com.team3.itability.mypage.entity.MemberProfileDTO;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MemberProfileDAO extends JpaRepository<MemberProfileDTO,Long> {

}
