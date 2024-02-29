package com.team3.itability.report.dao;

import com.team3.itability.report.dto.ReportDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportDAO extends JpaRepository<ReportDTO, Integer> {


}
