package com.team3.itability.mypage.entity;

//import com.team3.itability.mypage.MemberProfileDTO;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "career_dto")
@Table(name = "career")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CareerEntity {

    @Id
    @Column(name = "career_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private MemberProfileEntity memberId;

    public CareerEntity(String companyName, String startDate, String endDate, String role, String assignedTask, boolean isCurrentJob, MemberProfileEntity memberId) {
        this.companyName = companyName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.role = role;
        this.assignedTask = assignedTask;
        this.isCurrentJob = isCurrentJob;
        this.memberId = memberId;
    }
}

