package com.team3.itability.report.controller;

import com.team3.itability.report.aggregate.Report;
import com.team3.itability.report.dto.ReportDTO;
import com.team3.itability.report.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/report")
public class ReportController {

    private final ReportService reportService;

    @Autowired
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/add")
    public void addPage() {}

    @Transactional
    @PostMapping("/add")
    public String addReport(@ModelAttribute ReportDTO reportDTO, RedirectAttributes redirectAttributes) {
        reportService.addReport(reportDTO);
        redirectAttributes.addFlashAttribute("successMessage", "신고가 성공적으로 추가되었습니다.");
        return "redirect:/report/list"; // 신고 목록 페이지로 리다이렉트
    }

    @GetMapping("/list")
    public String getAllReports(Model model) {
        List<Report> reports = reportService.findAllReports();
        model.addAttribute("reports", reports);
        return "/report/list"; // Thymeleaf 템플릿 파일 경로
    }
    @GetMapping("/report/{reportId}")
    public String getReportById(@PathVariable Integer reportId, Model model) {
        return reportService.findReportById(reportId)
                .map(report -> {
                    model.addAttribute("report", report);
                    return "report/detail"; // Thymeleaf 템플릿 파일 경로
                })
                .orElse("redirect:/report/list"); // 신고 정보가 없는 경우 신고 목록으로 리다이렉트
    }


}