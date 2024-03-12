package com.team3.reportservice.report.aggregate;

import com.team3.reportservice.report.dto.ReportTargetType;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@Setter
@Entity
@Table(name="report")
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reportId;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date reportDate = new Date();

    @Column(nullable = false)
    private int reportCategoryId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ReportTargetType reportTargetType;

    @Column(nullable = false)
    private Long memberId;

    @Column(nullable = false)
    private Long reportTargetId;



}
