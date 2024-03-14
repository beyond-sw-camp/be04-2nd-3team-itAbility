package com.team3.memberservice.mypage.dao;

import com.team3.memberservice.mypage.entity.DegreeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DegreeDAO extends JpaRepository<DegreeEntity,Integer> {

}
