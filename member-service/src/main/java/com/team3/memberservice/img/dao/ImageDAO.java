package com.team3.memberservice.img.dao;

import com.team3.memberservice.img.entity.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageDAO extends JpaRepository<ImageEntity,Long> {

}
