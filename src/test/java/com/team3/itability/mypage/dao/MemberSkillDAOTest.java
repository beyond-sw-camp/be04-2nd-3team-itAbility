package com.team3.itability.mypage.dao;

import com.team3.itability.mypage.dto.DegreeDTO;
import com.team3.itability.mypage.dto.MemberSkillDTO;
import com.team3.itability.mypage.dto.SkillDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberSkillDAOTest {
    @Autowired
    private MemberSkillDAO memberSkillDAO;
    @Autowired
    private SkillDAO skillDAO;
    @Test
    void name() {
        List<MemberSkillDTO> memberSkillList = memberSkillDAO.findAll();
        memberSkillList.forEach(System.out::println);
        assertNotNull(memberSkillList);
    }
    @Test
    void name2(){
        List<SkillDTO> skillList = skillDAO.findAll();
        skillList.forEach(System.out::println);
        assertNotNull(skillList);
    }
}