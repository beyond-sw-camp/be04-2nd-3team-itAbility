package com.team3.itability.recruitment.repository;

import com.team3.itability.recruitment.aggregate.RecruitSkillDTO;
import com.team3.itability.recruitment.aggregate.RefRecruitCategoryDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RefRecruitRepoTest {

    @Autowired
    private RefRecruitRepo refRecruitRepo;

    @Autowired
    private RecruitSkillRepo recruitSkillRepo;

    @Test
    void main(){
        List<RefRecruitCategoryDTO> refList = refRecruitRepo.findAll();
        refList.forEach(System.out::println);
        assertNotNull(refList);
    }

    @Test
    void main2(){
        List<RecruitSkillDTO> recruitSkillList = recruitSkillRepo.findAll();
        recruitSkillList.forEach(System.out::println);
        assertNotNull(recruitSkillList);
    }


}