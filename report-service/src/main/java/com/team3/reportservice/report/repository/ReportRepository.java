package com.team3.reportservice.report.repository;

import com.team3.reportservice.report.aggregate.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository extends JpaRepository<Report, Integer> {


}
