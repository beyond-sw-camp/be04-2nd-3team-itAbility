package com.team3.itability.mypage.dao;

import com.team3.itability.mypage.entity.MemberSkillEntity;
import com.team3.itability.mypage.entity.SkillEntity;
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
        List<MemberSkillEntity> memberSkillList = memberSkillDAO.findAll();
        memberSkillList.forEach(System.out::println);
        assertNotNull(memberSkillList);
    }
    @Test
    void name2(){
        List<SkillEntity> skillList = skillDAO.findAll();
        skillList.forEach(System.out::println);
        assertNotNull(skillList);
    }
}