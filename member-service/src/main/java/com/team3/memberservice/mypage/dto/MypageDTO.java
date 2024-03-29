package com.team3.memberservice.mypage.dto;

import com.team3.memberservice.career.dto.CareerDTO;
import com.team3.memberservice.career.dto.ResponseCareer;
import com.team3.memberservice.skill.dto.ResponseSkill;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MypageDTO {

//    private MemberProfileDTO memberProfile;
//    private List<CareerDTO> careerDTOList;
//    private List<ResponseSkill> memberSkill;
//    private List<ResponseRecruitCategory> responseRecruitCategory;
    /*mypage 필요한 정보
    * memberInfo: 이름, 생일, 전화번호
    * memberProfile: 닉네임
    * career: 회사명, 입사일,퇴사일, 직급, 역할, 재직여부
    * skill: 스킬이름만
    * recruitCategory: 전문분야하나만
    * */
    private String image;
    private String name;
    private String nickname;
    private String phone;
    private String birthDate;

    private DegreeDTO degreeDTO;

    private List<ResponseCareer> careers;

    private List<String> skills;
    private List<String> recruitCategories;



    public MypageDTO(MemberProfileDTO profile, DegreeDTO degreeDTO,List<CareerDTO> careerDTOList, List<ResponseSkill> skillDTOS, List<ResponseRecruitCategory> recruitCategory) {
        this.image = profile.getImg().getPath();
        this.skills = new ArrayList<>();
        this.recruitCategories = new ArrayList<>();
        this.careers = new ArrayList<>();
        this.name= profile.getMemberInfo().getName();
        this.nickname = profile.getNickname();
        this.phone = profile.getMemberInfo().getPhone();
        this.birthDate = profile.getMemberInfo().getBirthDate();
        this.degreeDTO = degreeDTO;
        careerDTOList.forEach(career
                -> this.careers.add(new ResponseCareer(career.getCareerId(),career.getCompanyName(), career.getStartDate()
                                 , career.getEndDate(), career.getRole(), career.getAssignedTask()
                                 , career.isCurrentJob())));

        skillDTOS.forEach(skillDTO -> this.skills.add(skillDTO.getSkillName()));
        recruitCategory.forEach(recCate-> this.recruitCategories.add(recCate.getRecruitName()));
    }
}
