package com.team3.itability.mypage.service;

import com.team3.itability.mypage.dao.MemberProfileDAO;
import com.team3.itability.mypage.dao.MemberSkillDAO;
import com.team3.itability.mypage.entity.MemberProfileEntity;
import com.team3.itability.mypage.entity.MemberSkillEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
class MypageServiceTest {
    static Long memberCode;
    private static EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    @Autowired
    private MemberProfileDAO memberProfileDAO;
    @Autowired
    private MemberSkillDAO memberSkillDAO;
    @BeforeAll
    public static void init(){
        memberCode = 1L;
    }

    /**
     * <h2>1. 자신 마이페이지 정보 출력</h2>
     * */
    @Test
    void printMypageData(){
        MemberProfileEntity memberProfileEntity = memberProfileDAO.findById(memberCode).orElseThrow();
        System.out.println("memberProfileDTO = " + memberProfileEntity);
    }

    /** <h2>2. 닉네임 설정</h2>
     *
     */
    @Transactional
    @Test
    void updateNickname(){
        MemberProfileEntity memberProfileEntity = memberProfileDAO.findById(memberCode).orElseThrow();
        String name = "nick";
//        memberProfileDTO.setNickname(name);
        System.out.println("memberProfileDTO = " + memberProfileEntity);
        MemberProfileEntity memberProfileEntity2 = memberProfileDAO.findById(memberCode).orElseThrow();
        System.out.println("memberProfileDTO2 = " + memberProfileEntity2);
    }

    /**3. 프로필 사진 등록 및 수정*/
    @Transactional
    @Test
    void updateProfileImage(){
        MemberProfileEntity memberProfileEntity = memberProfileDAO.findById(memberCode).orElseThrow();
    }

    @Test
    void printskills(){

        List<MemberSkillEntity> memberSkills = memberSkillDAO.findByIdMemberId(1L);
        memberSkills.forEach(System.out::println);
    }

}

