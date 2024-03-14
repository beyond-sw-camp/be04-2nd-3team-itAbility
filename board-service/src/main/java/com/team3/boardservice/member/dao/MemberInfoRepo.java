package com.team3.boardservice.member.dao;

import com.team3.boardservice.member.dto.MemberInfoDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberInfoRepo extends JpaRepository<MemberInfoDTO,Long> {
    List<MemberInfoDTO> findByMbReportCountGreaterThanEqual(int mbReportCount);
}
