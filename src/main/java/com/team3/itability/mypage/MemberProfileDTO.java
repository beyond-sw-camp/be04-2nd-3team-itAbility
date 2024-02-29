package com.team3.itability.mypage;


import com.team3.itability.member.dto.MemberInfoDTO;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString


@Entity(name="member_profile_dto")
@Table(name="member_profile")
public class MemberProfileDTO {

    @Id
    @OneToOne
    @JoinColumn (name="member_id")
    private MemberInfoDTO memberId;
}
