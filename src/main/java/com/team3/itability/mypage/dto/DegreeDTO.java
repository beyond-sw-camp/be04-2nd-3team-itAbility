package com.team3.itability.mypage.dto;


import jakarta.persistence.*;
import lombok.*;

@Entity(name = "degree_dto")
@Table(name = "degree")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DegreeDTO {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name="degree_id")
    private int degreeId;
    @Column(name="final_edu_name")
    private String finalEduName;
    @Column(name="enroll_date")
    private String enrollDate;
    @Column(name="graduate_date")
    private String graduateDate;
    @Column(name="major")
    private String major;
    @Column(name="register_status")
    private boolean registerStatus;

}

