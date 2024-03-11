package com.team3.itability.mypage.dao;

//import com.team3.itability.mypage.dto.MemberProfileDTO;
import com.team3.itability.mypage.entity.MemberRecruitCategoryEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberRecruitCategoryDTODAOTest
{
    @Autowired
    MemberRecruitCategoryDAO memberRecruitCategoryDAO;
    @Test
    void name() {
        List<MemberRecruitCategoryEntity> memberRecruitCategoryList = memberRecruitCategoryDAO.findAll();
        memberRecruitCategoryList.forEach(System.out::println);
        assertNotNull(memberRecruitCategoryList);
    }
}