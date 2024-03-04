package com.team3.itability.report.controller;

import com.team3.itability.report.dto.ReportDTO;
import com.team3.itability.report.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @GetMapping("/list")
    public String findMenuList(Model model) {

        List<ReportDTO> reportList = reportService.findReportList();
        model.addAttribute("reportList", reportList);

        return "/report/list";
    }

    @GetMapping("/add")
    public String showAddReportForm(Model model) {
        model.addAttribute("reportDTO", new ReportDTO());
        return "report/add";
    }

    @PostMapping("/add")
    public String addReport(ReportDTO reportDTO, RedirectAttributes redirectAttributes) {
        reportService.addReport(reportDTO);
        redirectAttributes.addFlashAttribute("message", "신고글이 성공적으로 추가되었습니다.");
        return "redirect:/report/list"; // 신고글 목록 페이지로 리다이렉션
    }

}