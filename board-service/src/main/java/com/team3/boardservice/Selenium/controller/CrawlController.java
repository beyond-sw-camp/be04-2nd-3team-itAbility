package com.team3.boardservice.Selenium.controller;

import com.team3.boardservice.Selenium.service.CrawlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
}