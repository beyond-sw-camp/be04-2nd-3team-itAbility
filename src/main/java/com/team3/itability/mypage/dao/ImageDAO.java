package com.team3.itability.mypage.dao;

import com.team3.itability.mypage.entity.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageDAO extends JpaRepository<ImageEntity,Integer> {

}
