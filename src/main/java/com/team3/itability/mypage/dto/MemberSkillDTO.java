package com.team3.itability.mypage.dto;

//import com.team3.itability.mypage.MemberProfileDTO;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "member_skill_dto")
@Table(name = "member_skill")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MemberSkillDTO {
    @EmbeddedId
    private MemberSkillId memberId;

    @MapsId("memberId")
    @ManyToOne
    @JoinColumn(name = "member_id")
    private MemberProfileDTO memberProfile;

    @MapsId("skillId")
    @ManyToOne
    @JoinColumn(name = "skill_id")
    private SkillDTO skill;

}
