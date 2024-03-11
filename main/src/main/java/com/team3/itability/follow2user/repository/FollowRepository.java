package com.team3.itability.follow2user.repository;

import com.team3.itability.follow2user.aggregate.Follow;
import com.team3.itability.follow2user.dto.FollowDTO;
import com.team3.itability.member.dto.MemberInfoDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface FollowRepository extends JpaRepository<Follow, Integer> {
    List<Follow> findByFollowing(MemberInfoDTO following);
}
