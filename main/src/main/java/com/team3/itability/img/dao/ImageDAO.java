package com.team3.itability.img.dao;

import com.team3.itability.img.entity.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageDAO extends JpaRepository<ImageEntity,Integer> {

}
