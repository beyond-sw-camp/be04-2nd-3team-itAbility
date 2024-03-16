package com.team3.reportservice.report.repository;

import com.team3.reportservice.report.aggregate.Member;
import com.team3.reportservice.vo.ResponseMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResponseMemberRepository extends JpaRepository<Member, Long> {
    List<Member> findByMbReportCountGreaterThanEqual(int i);
}
