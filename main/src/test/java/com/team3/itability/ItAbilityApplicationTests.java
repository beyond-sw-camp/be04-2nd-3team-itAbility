package com.team3.itability;

import com.team3.itability.mypage.dao.DegreeDAO;
import com.team3.itability.mypage.entity.DegreeEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ItAbilityApplicationTests {

    @Autowired
    private DegreeDAO degreeDAO;

    @Test
    void contextLoads() {
    }

    @Test
    void name(){
        List<DegreeEntity> memoList = degreeDAO.findAll();
        memoList.forEach(System.out::println);
        assertNotNull(memoList);
    }
}
