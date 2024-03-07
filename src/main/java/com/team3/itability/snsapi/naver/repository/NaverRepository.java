package com.team3.itability.snsapi.naver.repository;

import com.team3.itability.snsapi.naver.aggregate.NaverEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NaverRepository extends JpaRepository<NaverEntity, Long> {
}
