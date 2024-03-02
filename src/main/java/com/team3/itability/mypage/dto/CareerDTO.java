package com.team3.itability.mypage.dto;

//import com.team3.itability.mypage.MemberProfileDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "career_dto")
@Table(name = "career")
@Getter
@Setter
public class CareerDTO {

    @Id
    @Column(name = "career_id")
    private int careerId;
    @Column(name = "company_name")
    private String companyName;
    @Column(name = "start_date")
    private String startDate;
    @Column(name = "end_date")
    private String endDate;
    @Column(name= "role")
    private String role;
    @Column(name = "assigned_task")
    private String assignedTask;
    @Column(name ="is_current_job")
    private boolean isCurrentJob;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private MemberProfileDTO memberId;

    public CareerDTO() {
    }

    public CareerDTO(int careerId, String companyName, String startDate, String endDate, String role, String assignedTask, boolean isCurrentJob, MemberProfileDTO memberId) {
        this.careerId = careerId;
        this.companyName = companyName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.role = role;
        this.assignedTask = assignedTask;
        this.isCurrentJob = isCurrentJob;
        this.memberId = memberId;
    }

    public int getCareerId() {
        return careerId;
    }

    public void setCareerId(int careerId) {
        this.careerId = careerId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getAssignedTask() {
        return assignedTask;
    }

    public void setAssignedTask(String assignedTask) {
        this.assignedTask = assignedTask;
    }

    public boolean isCurrentJob() {
        return isCurrentJob;
    }

    public void setCurrentJob(boolean currentJob) {
        isCurrentJob = currentJob;
    }

    public MemberProfileDTO getMemberId() {
        return memberId;
    }

    public void setMemberId(MemberProfileDTO memberId) {
        this.memberId = memberId;
    }

    @Override
    public String toString() {
        return "CareerDTO{" +
                "careerId=" + careerId +
                ", companyName='" + companyName + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", role='" + role + '\'' +
                ", assignedTask='" + assignedTask + '\'' +
                ", isCurrentJob=" + isCurrentJob +
                ", memberId=" + memberId +
                '}';
    }
}

