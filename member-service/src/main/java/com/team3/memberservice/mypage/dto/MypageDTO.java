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
    private MemberAndRemainSkillDTO memberSkillDTO;
    private MemberAndRemainRecruitCategoryDTO recruitCategoryDTO;

    public MypageDTO(MemberProfileDTO memberProfileDTO, List<CareerDTO> careerDTOList, MemberAndRemainSkillDTO memberSkillDTO) {
        this.memberProfileDTO = memberProfileDTO;
        this.careerDTOList = careerDTOList;
        this.memberSkillDTO = memberSkillDTO;
    }
}
