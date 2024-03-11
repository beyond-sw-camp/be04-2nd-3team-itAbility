package com.team3.itability.mypage.dao;

import com.team3.itability.img.dao.ImageDAO;
import com.team3.itability.mypage.entity.MemberProfileEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberProfileDTODAOTest {

    @Autowired
    MemberProfileDAO memberProfileDAO;
    @Autowired
    DegreeDAO degreeDAO;
    @Autowired
    CareerDAO careerDAO;
    @Autowired
    MemberRecruitCategoryDAO memberRecruitCategoryDAO;
    @Autowired
    ImageDAO imageDAO;

    private Long memberCode =1L;

    @Test
    void name() {
        List<MemberProfileEntity> profileList = memberProfileDAO.findAll();
        profileList.forEach(System.out::println);
        assertNotNull(profileList);
    }

    /**
     * <h2>showAll()</h2>
     * 프로필 관련 정보들 전부 불러오기 위한 것
     * */
//    @Test
//    void showAll(){
//        //userCode = 1;
//        MemberProfileDTO member = memberProfileDAO.findById(memberCode).orElseThrow();
//        System.out.println("member = " + member);
//
//    }

}
