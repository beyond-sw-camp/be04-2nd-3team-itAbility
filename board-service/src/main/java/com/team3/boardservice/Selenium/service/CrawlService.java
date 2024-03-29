package com.team3.boardservice.Selenium.service;

//import com.team3.boardservice.Selenium.entity.CrawlEntity;
//import com.team3.boardservice.Selenium.repository.CrawlRepository;
import com.team3.boardservice.Selenium.entity.CrawlEntity;
import com.team3.boardservice.Selenium.repository.CrawlRepository;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CrawlService {

    private final WebDriver webDriver;
    private final CrawlRepository crawlRepository;

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CrawlService(WebDriver webDriver, CrawlRepository crawlRepository, JdbcTemplate jdbcTemplate) {
        this.webDriver = webDriver;
        this.crawlRepository = crawlRepository;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional
    public void crawlJobListings() throws InterruptedException {
        jdbcTemplate.update("DELETE FROM job_listing");
        // AUTO_INCREMENT 리셋
        jdbcTemplate.update("ALTER TABLE job_listing AUTO_INCREMENT = 1");
        webDriver.get("https://www.wanted.co.kr/wdlist/518?country=kr&job_sort=job.recommend_order&years=-1&locations=all");

        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

        Thread.sleep(6000);

        List<WebElement> jobCards = webDriver.findElements(By.className("Card_Card__lU7z_"));

        for (WebElement card : jobCards) {
            String positionName = card.findElement(By.cssSelector(".JobCard_JobCard__body__position__P8R0W")).getText();
            String companyName = card.findElement(By.cssSelector(".JobCard_JobCard__body__company__F6XoH")).getText();
            WebElement imgElement = card.findElement(By.tagName("img"));
            String imgSrc = imgElement.getAttribute("src");
            WebElement linkElement = card.findElement(By.cssSelector("a[data-attribute-id='position__click']"));
            String href = linkElement.getAttribute("href");

            System.out.println("직무 제목: " + positionName);
            System.out.println("회사 이름: " + companyName);
            System.out.println("이미지 URL: " + imgSrc);
            System.out.println("상세 페이지 링크: " + href);
            System.out.println("=====================================");



            CrawlEntity jobListing = new CrawlEntity();
            jobListing.setPositionName(card.findElement(By.cssSelector(".JobCard_JobCard__body__position__P8R0W")).getText());
            jobListing.setCompanyName(card.findElement(By.cssSelector(".JobCard_JobCard__body__company__F6XoH")).getText());
            jobListing.setImgSrc(card.findElement(By.tagName("img")).getAttribute("src"));
//            WebElement linkElement = card.findElement(By.cssSelector("a[data-attribute-id='position__click']"));
            jobListing.setHref(linkElement.getAttribute("href"));

            crawlRepository.save(jobListing); // 데이터베이스에 저장

        }

        webDriver.quit();
    }

    public List<CrawlEntity> findAllJobListings() {
        return crawlRepository.findAll();
    }
}