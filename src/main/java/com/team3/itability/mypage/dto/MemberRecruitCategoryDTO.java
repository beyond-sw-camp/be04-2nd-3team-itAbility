package com.team3.itability.mypage.dto;

//import com.team3.itability.mypage.MemberProfileDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity(name = "member_recruit_category_dtd")
@Table(name = "member_recruit_category")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MemberRecruitCategoryDTO {
    @EmbeddedId
    private memberRecruitCategoryId memberId;
    @MapsId("memberId")
    @ManyToOne
    @JoinColumn(name = "member_id")
    private MemberProfileDTO memberProfile;

    @MapsId("recruitCategoryId")
    @ManyToOne
    @JoinColumn(name = "recruit_category_id")
    private SkillDTO skill;

    public MemberRecruitCategoryDTO() {
    }

    public MemberRecruitCategoryDTO(memberRecruitCategoryId memberId, MemberProfileDTO memberProfile, SkillDTO skill) {
        this.memberId = memberId;
        this.memberProfile = memberProfile;
        this.skill = skill;
    }

    public memberRecruitCategoryId getMemberId() {
        return memberId;
    }

    public void setMemberId(memberRecruitCategoryId memberId) {
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
        return "MemberRecruitCategoryDTO{" +
                "memberId=" + memberId +
                ", memberProfile=" + memberProfile +
                ", skill=" + skill +
                '}';
    }
}
