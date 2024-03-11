package com.team3.itability.report.controller;

import com.team3.itability.report.aggregate.Report;
import com.team3.itability.report.dto.ReportDTO;
import com.team3.itability.report.service.ReportService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/report")
public class ReportController {

    private final ReportService reportService;
    private final ModelMapper modelMapper;

    @Autowired
    public ReportController(ReportService reportService, ModelMapper modelMapper) {
        this.reportService = reportService;
        this.modelMapper = modelMapper;
    }

    @Transactional
    @PostMapping("/add")
    public ResponseEntity<String> addReport(@RequestBody ReportDTO reportDTO) {
        reportService.createOrUpdateReport(reportDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("신고가 성공적으로 추가되었습니다.");
    }

    @GetMapping("/{reportId}")
    public ResponseEntity<?> getReportById(@PathVariable Integer reportId) {
        Optional<Report> reportOptional = reportService.findReportById(reportId);

        if (reportOptional.isPresent()) {
            ReportDTO reportDTO = modelMapper.map(reportOptional.get(), ReportDTO.class);
            return ResponseEntity.ok(reportDTO);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Report not found");
        }
    }
    @GetMapping("/list")
    public ResponseEntity<List<ReportDTO>> findReportList() {
        List<ReportDTO> reportList = reportService.findReportList();
        return ResponseEntity.ok().body(reportList);
    }
}
