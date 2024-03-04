package com.team3.itability.member.dao;

import com.team3.itability.member.dto.MemberInfoDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberInfoRepo extends JpaRepository<MemberInfoDTO,Long> {
}
