package com.team3.itability.mypage.dao;

import com.team3.itability.mypage.dto.CareerDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CareerDAO extends JpaRepository<CareerDTO,Integer> {

}
