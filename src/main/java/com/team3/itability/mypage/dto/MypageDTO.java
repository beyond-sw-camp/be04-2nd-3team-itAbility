package com.team3.itability.mypage.dto;

import com.team3.itability.img.dto.ImageDTO;
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
}
