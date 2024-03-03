package com.team3.itability.mypage.dao;

import com.team3.itability.mypage.dto.CareerDTO;
import com.team3.itability.mypage.dto.MemberProfileDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CareerDAO extends JpaRepository<CareerDTO,Integer> {

    List<CareerDTO> findByMemberId(MemberProfileDTO member);

}
