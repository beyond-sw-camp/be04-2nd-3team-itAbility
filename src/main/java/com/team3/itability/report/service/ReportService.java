package com.team3.itability.report.service;

import com.team3.itability.follow2user.aggregate.Follow;
import com.team3.itability.follow2user.dto.FollowDTO;
import com.team3.itability.member.dao.MemberInfoRepo;
import com.team3.itability.member.dto.MemberInfoDTO;
import com.team3.itability.report.aggregate.Report;
import com.team3.itability.report.dto.ReportDTO;
import com.team3.itability.report.dto.ReportTargetType;
import com.team3.itability.report.repository.ReportRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReportService {


    private final ReportRepository reportRepository;
    private final ModelMapper modelMapper;
    private final MemberInfoRepo memberRepository;
    @Autowired
    public ReportService(ReportRepository reportRepository, ModelMapper modelMapper, MemberInfoRepo memberRepository) {
        this.reportRepository = reportRepository;
        this.modelMapper = modelMapper;
        this.memberRepository = memberRepository;
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

        Long targetMemberId = reportDTO.getReportTargetId();
        MemberInfoDTO member = memberRepository.findById(targetMemberId)
                .orElseThrow(() -> new RuntimeException("멤버를 찾을 수 없습니다."));
        member.setMbReportCount(member.getMbReportCount() + 1);
        memberRepository.save(member);


        return reportRepository.save(report);
    }

    public List<ReportDTO> findReportList() {
        List<Report> reportList = reportRepository.findAll(Sort.by("reportId"));

        return reportList.stream().map(report -> modelMapper.map(report, ReportDTO.class)).collect(Collectors.toList());
    }
}
