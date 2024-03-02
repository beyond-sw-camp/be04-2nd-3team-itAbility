package com.team3.itability.mypage.dto;


import jakarta.persistence.*;
import lombok.*;

@Entity(name = "degree_dto")
@Table(name = "degree")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//@ToString
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


    public int getDegreeId() {
        return degreeId;
    }

    public void setDegreeId(int degreeId) {
        this.degreeId = degreeId;
    }

    public String getFinalEduName() {
        return finalEduName;
    }

    public void setFinalEduName(String finalEduName) {
        this.finalEduName = finalEduName;
    }

    public String getEnrollDate() {
        return enrollDate;
    }

    public void setEnrollDate(String enrollDate) {
        this.enrollDate = enrollDate;
    }

    public String getGraduateDate() {
        return graduateDate;
    }

    public void setGraduateDate(String graduateDate) {
        this.graduateDate = graduateDate;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public boolean isRegisterStatus() {
        return registerStatus;
    }

    public void setRegisterStatus(boolean registerStatus) {
        this.registerStatus = registerStatus;
    }

    @Override
    public String toString() {
        return "DegreeDTO{" +
                "degreeId=" + degreeId +
                ", finalEduName='" + finalEduName + '\'' +
                ", enrollDate='" + enrollDate + '\'' +
                ", graduateDate='" + graduateDate + '\'' +
                ", major='" + major + '\'' +
                ", registerStatus=" + registerStatus +
                '}';
    }
}

