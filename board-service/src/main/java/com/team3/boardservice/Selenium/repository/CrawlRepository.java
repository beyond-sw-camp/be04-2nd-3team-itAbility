package com.team3.boardservice.Selenium.repository;

import com.team3.boardservice.Selenium.entity.CrawlEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CrawlRepository extends JpaRepository<CrawlEntity, Long> {
}
