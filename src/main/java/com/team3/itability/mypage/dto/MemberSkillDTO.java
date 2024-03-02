package com.team3.itability.mypage.dto;

//import com.team3.itability.mypage.MemberProfileDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "member_skill_dto")
@Table(name = "member_skill")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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
