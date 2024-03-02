package com.team3.itability.mypage.dao;

import com.team3.itability.mypage.dto.DegreeDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DegreeDAO extends JpaRepository<DegreeDTO,Integer> {

}
