package com.team3.itability.report.repository;

import com.team3.itability.report.aggregate.Report;
import com.team3.itability.report.dto.ReportDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository extends JpaRepository<Report, Integer> {


}
