package com.team3.itability.mypage.dao;

import com.team3.itability.mypage.dto.MemberProfileDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberProfileDAOTest {

    @Autowired
    MemberProfileDAO memberProfileDAO;

    @Test
    void name() {
        List<MemberProfileDTO> profileList = memberProfileDAO.findAll();
        profileList.forEach(System.out::println);
        assertNotNull(profileList);
    }
}
