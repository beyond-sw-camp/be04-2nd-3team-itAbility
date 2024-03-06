package com.team3.itability.follow2user.dao;

import com.team3.itability.follow2user.dto.FollowDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowDAO extends JpaRepository<FollowDTO, Integer> {

}
