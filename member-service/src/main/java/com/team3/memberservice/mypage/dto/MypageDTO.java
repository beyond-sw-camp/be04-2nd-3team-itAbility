package com.team3.memberservice.mypage.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MypageDTO {

    private MemberProfileDTO memberProfileDTO;
    private List<CareerDTO> careerDTOList;
    private List<ResponseSkill> memberSkillDTO;

}
