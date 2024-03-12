package com.team3.boardservice.recruitment.aggregate;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity(name = "recruit_category")
@Table(name = "recruit_category")
public class RecruitCategoryDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recruit_category_id")
    private Integer recruitCategoryId;

    @Column(name = "recruit_name")
    private String recruitName;
}
