package com.team3.boardservice.recruitment.aggregate;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(exclude = "ref_recruit_category")
@Entity(name = "recruit")
@Table(name = "recruit")
public class RecruitDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recruit_id")
    private Integer recruitId;

    @Enumerated(EnumType.STRING)
    @Column(name = "recruit_type")
    private RecruitType recruitType;

    @Column(name = "recruit_title")
    private String recruitTitle;

    @Column(name = "recruit_content")
    private String recruitContent;

    @Column(name = "recruit_expdate")
    private String recruitExpDate;

    @Column(name = "recruit_mb_cnt")
    private Integer recruitMbCnt;

    @Column(name="member_id")
    private long memberInfoDTO;

    public RecruitDTO(RecruitType recruitType, String recruitTitle, String recruitContent, String recruitExpDate, Integer recruitMbCnt, long memberInfoDTO) {
        this.recruitType = recruitType;
        this.recruitTitle = recruitTitle;
        this.recruitContent = recruitContent;
        this.recruitExpDate = recruitExpDate;
        this.recruitMbCnt = recruitMbCnt;
        this.memberInfoDTO = memberInfoDTO;
    }
}
