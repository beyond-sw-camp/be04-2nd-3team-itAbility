package com.team3.boardservice.Selenium.controller;

import com.team3.boardservice.Selenium.entity.CrawlEntity;
import com.team3.boardservice.Selenium.service.CrawlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CrawlController {

    private final CrawlService seleniumService;

    @Autowired
    public CrawlController(CrawlService seleniumService) {
        this.seleniumService = seleniumService;
    }

    @GetMapping("/crawl-jobs")
    public ResponseEntity<String> crawlJobs() {
        try {
            seleniumService.crawlJobListings();
            return ResponseEntity.ok("Crawling Job Listings Completed Successfully.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // 재설정
            return ResponseEntity.internalServerError().body("Crawling Job Listings Failed.");
        }
    }

    @GetMapping("/crawl")
    public String startCrawling() {
        try {
            seleniumService.crawlJobListings();
            return "Crawling started successfully!";
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return "Crawling interrupted: " + e.getMessage();
        }
    }

    @GetMapping("/job-listings")
    public List<CrawlEntity> getAllJobListings() {
        return seleniumService.findAllJobListings();
    }
}