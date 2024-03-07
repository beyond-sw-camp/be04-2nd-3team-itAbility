package com.team3.itability.report.service;

import com.team3.itability.report.aggregate.Report;
import com.team3.itability.report.dto.ReportDTO;
import com.team3.itability.report.dto.ReportTargetType;
import com.team3.itability.report.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ReportService {


    private final ReportRepository reportRepository;
    @Autowired
    public ReportService(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    public void createReport(Report report) {
        reportRepository.save(report);
    }

    public List<Report> findAllReports() {
        return reportRepository.findAll();
    }

    public Optional<Report> findReportById(Integer reportId) {
        return reportRepository.findById(reportId);
    }

    public Report addReport(ReportDTO reportDTO) {
        Report report = new Report();
        report.setReportDate(reportDTO.getReportDate());
        report.setReportCategoryId(reportDTO.getReportCategoryId());
        report.setReportTargetType(reportDTO.getReportTargetType());
        report.setMemberId(reportDTO.getMemberId());
        report.setReportTargetId(reportDTO.getReportTargetId());
        return reportRepository.save(report);
    }

}
