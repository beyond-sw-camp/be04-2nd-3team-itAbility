package com.team3.itability.mypage.entity;

//import com.team3.itability.mypage.MemberProfileDTO;
import jakarta.persistence.*;
import lombok.*;


@Entity(name = "member_recruit_category_dto")
@Table(name = "member_recruit_category")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MemberRecruitCategoryEntity {
    @EmbeddedId
    private MemberRecruitCategoryId Id;

}
