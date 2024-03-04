package com.team3.itability.mypage.dto;

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

}

