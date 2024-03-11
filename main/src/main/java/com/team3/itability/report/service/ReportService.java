package com.team3.itability.report.service;

import com.team3.itability.member.dto.MemberInfoDTO;
import com.team3.itability.report.dto.ReportDTO;
import com.team3.itability.report.aggregate.Report;
import com.team3.itability.report.repository.ReportRepository;
import com.team3.itability.member.dao.MemberInfoRepo;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

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

    public Report createOrUpdateReport(ReportDTO reportDTO) {
        Report report = modelMapper.map(reportDTO, Report.class);

        updateMemberReportCount(reportDTO.getReportTargetId());

        return reportRepository.save(report);
    }

    private void updateMemberReportCount(Long memberId) {
        MemberInfoDTO member = memberRepository.findById(memberId)
                .orElseThrow(() -> new EntityNotFoundException("멤버를 찾을 수 없습니다."));
        member.setMbReportCount(member.getMbReportCount() + 1);
        memberRepository.save(member);
    }

    public Optional<Report> findReportById(Integer reportId) {
        return reportRepository.findById(reportId);
    }

    // ReportService.java 수정
    public List<ReportDTO> findReportList() { // 메서드 이름 변경
        List<Report> reportList = reportRepository.findAll(Sort.by("reportId"));
        return reportList.stream()
                .map(report -> modelMapper.map(report, ReportDTO.class))
                .collect(Collectors.toList());
    }
}
