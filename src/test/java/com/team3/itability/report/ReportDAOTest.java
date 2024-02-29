package com.team3.itability.report;

import com.team3.itability.report.dao.ReportDAO;
import com.team3.itability.report.dto.ReportDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReportDAOTest {

    @Autowired
    private ReportDAO reportDAO;

    @Test
    void main(){
        List<ReportDTO> reportList = reportDAO.findAll();
        reportList.forEach(System.out::println);
        assertNotNull(reportList);
    }
}