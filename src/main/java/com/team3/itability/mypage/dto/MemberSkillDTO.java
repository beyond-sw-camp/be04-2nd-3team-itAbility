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
    private MemberSkillId id;
//    @MapsId("memberId")
//    @ManyToOne
//    @JoinColumn(name = "member_id")
//    @Column(nullable = false)
//    private long member_id;

//    @MapsId("skillId")
//    @ManyToOne
//    @JoinColumn(name = "skill_id")
//    @Column(nullable = false)
//    private int skill;

}
