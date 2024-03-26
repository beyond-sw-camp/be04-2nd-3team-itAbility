package com.team3.boardservice.Selenium.entity;

import jakarta.persistence.*;
import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity(name = "JobListing")
@Table(name = "job_listing")
public class CrawlEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="position_name")
    private String positionName;

    @Column(name="company_name")
    private String companyName;

    @Column(name="img_src")
    private String imgSrc;

    @Column(name="href")
    private String href;

}
