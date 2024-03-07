package com.team3.itability.feed.repository;


import com.team3.itability.feed.dto.LikeDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepo extends JpaRepository<LikeDTO,Integer> {
}
