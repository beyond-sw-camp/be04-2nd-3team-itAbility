package com.team3.itability.report.service;

import com.team3.itability.report.dao.ReportDAO;
import com.team3.itability.report.dto.ReportDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReportService {

    private final ReportDAO reportDAO;

    @Autowired
    public ReportService( ReportDAO reportDAO) {
        this.reportDAO = reportDAO;
    }

    @Transactional(readOnly = true)
    public List<ReportDTO> findReportList() {
        return reportDAO.findAll(Sort.by("reportId"));
    }
}
