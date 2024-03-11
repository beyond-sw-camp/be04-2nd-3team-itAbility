//package com.team3.itability.report.controller;
//
//import com.team3.itability.report.dto.ReportDTO;
//import com.team3.itability.report.service.ReportService;
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/report")
//public class ReportController {
//
//    private final ReportService reportService;
//    private final ModelMapper modelMapper;
//
//    @Autowired
//    public ReportController(ReportService reportService, ModelMapper modelMapper) {
//        this.reportService = reportService;
//        this.modelMapper = modelMapper;
//    }
//
//    @PostMapping("/add")
//    public ResponseEntity<String> addReport(@RequestBody ReportDTO reportDTO) {
//        reportService.createOrUpdateReport(reportDTO);
//        return ResponseEntity.status(HttpStatus.CREATED).body("신고가 성공적으로 추가되었습니다.");
//    }
//
////    @GetMapping("/{reportId}")
////    public ResponseEntity<Object> getReportById(@PathVariable Integer reportId) {
////        return reportService.findReportById(reportId)
////                .map(report -> modelMapper.map(report, ReportDTO.class)) // Report 객체를 ReportDTO로 변환
////                .map(reportDTO -> ResponseEntity.ok().body(reportDTO)) // 변환된 DTO를 응답 본문으로 설정
////                .orElse(new ResponseEntity<>("Report not found", HttpStatus.NOT_FOUND)); // 찾지 못한 경우의 응답
////    }
//
//    @GetMapping("/list")
//    public ResponseEntity<List<ReportDTO>> findReportList() {
//        List<ReportDTO> reportList = reportService.findReportList();
//        return ResponseEntity.ok().body(reportList);
//    }
//}
