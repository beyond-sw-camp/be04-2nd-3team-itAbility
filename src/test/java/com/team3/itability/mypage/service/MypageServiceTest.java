package com.team3.itability.mypage.service;

import com.team3.itability.mypage.dao.MemberProfileDAO;
import com.team3.itability.mypage.dto.MemberProfileDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class MypageServiceTest {
    static int memberCode;
    private static EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    @Autowired
    private MemberProfileDAO memberProfileDAO;

    @BeforeAll
    public static void init(){
        memberCode = 1;
    }

    /**
     * <h2>1. 자신 마이페이지 정보 출력</h2>
     * */
    @Test
    void printMypageData(){
        MemberProfileDTO memberProfileDTO = memberProfileDAO.findById(memberCode).orElseThrow();
        System.out.println("memberProfileDTO = " + memberProfileDTO);
    }

    /** <h2>2. 닉네임 설정</h2>
     *
     */
    @Transactional
    @Test
    void updateNickname(){
        MemberProfileDTO memberProfileDTO = memberProfileDAO.findById(memberCode).orElseThrow();
        String name = "nick";
//        memberProfileDTO.setNickname(name);
        System.out.println("memberProfileDTO = " + memberProfileDTO);
        MemberProfileDTO memberProfileDTO2 = memberProfileDAO.findById(memberCode).orElseThrow();
        System.out.println("memberProfileDTO2 = " + memberProfileDTO2);
    }

    /**3. 프로필 사진 등록 및 수정*/
    @Transactional
    @Test
    void updateProfileImage(){
        MemberProfileDTO memberProfileDTO = memberProfileDAO.findById(memberCode).orElseThrow();
    }


}

