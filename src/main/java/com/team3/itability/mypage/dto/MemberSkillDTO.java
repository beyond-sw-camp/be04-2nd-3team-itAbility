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

    public MemberSkillDTO() {
    }

    public MemberSkillDTO(MemberSkillId memberId, MemberProfileDTO memberProfile, SkillDTO skill) {
        this.memberId = memberId;
        this.memberProfile = memberProfile;
        this.skill = skill;
    }

    public MemberSkillId getMemberId() {
        return memberId;
    }

    public void setMemberId(MemberSkillId memberId) {
        this.memberId = memberId;
    }

    public MemberProfileDTO getMemberProfile() {
        return memberProfile;
    }

    public void setMemberProfile(MemberProfileDTO memberProfile) {
        this.memberProfile = memberProfile;
    }

    public SkillDTO getSkill() {
        return skill;
    }

    public void setSkill(SkillDTO skill) {
        this.skill = skill;
    }

    @Override
    public String toString() {
        return "MemberSkillDTO{" +
                "memberId=" + memberId +
                ", memberProfile=" + memberProfile +
                ", skill=" + skill +
                '}';
    }
}
