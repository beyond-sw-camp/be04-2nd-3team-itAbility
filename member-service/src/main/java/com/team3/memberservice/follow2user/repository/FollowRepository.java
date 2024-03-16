package com.team3.memberservice.follow2user.repository;

import com.team3.memberservice.follow2user.aggregate.Follow;
import com.team3.memberservice.follow2user.dto.FollowDTO;
import com.team3.memberservice.member.dto.MemberInfoDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface FollowRepository extends JpaRepository<Follow, Integer> {
    List<Follow> findByFollowing(MemberInfoDTO following);
    List<Follow> findByFollowed(MemberInfoDTO followed);
}
