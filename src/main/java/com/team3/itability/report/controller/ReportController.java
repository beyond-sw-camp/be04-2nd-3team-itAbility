package com.team3.itability.report.controller;

import com.team3.itability.report.dto.ReportDTO;
import com.team3.itability.report.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

}