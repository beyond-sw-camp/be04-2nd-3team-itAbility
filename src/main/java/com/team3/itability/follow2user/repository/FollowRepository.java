package com.team3.itability.follow2user.repository;

import com.team3.itability.follow2user.aggregate.Follow;
import com.team3.itability.follow2user.dto.FollowDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface FollowRepository extends JpaRepository<Follow, Integer> {

}
