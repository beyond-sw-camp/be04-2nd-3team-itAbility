package com.team3.itability.mypage.dto;

//import com.team3.itability.mypage.MemberProfileDTO;
import jakarta.persistence.*;
import lombok.*;


@Entity(name = "member_recruit_category_dtd")
@Table(name = "member_recruit_category")
@Getter
@Setter
@ToString
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


}
