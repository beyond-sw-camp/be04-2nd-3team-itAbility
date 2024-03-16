package com.team3.memberservice.mypage.dto;

import com.team3.memberservice.skill.dto.ResponseSkill;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MypageDTO {

    private MemberProfileDTO memberProfile;
    private List<CareerDTO> careerDTOList;
    private List<ResponseSkill> memberSkill;
    private List<ResponseRecruitCategory> responseRecruitCategory;

}
