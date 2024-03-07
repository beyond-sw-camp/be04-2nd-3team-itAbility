package com.team3.itability.mypage.dao;

import com.team3.itability.mypage.entity.CareerEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CareerDTOEntityTest {
    @Autowired
    CareerDAO careerDAO;

    @Test
    void name(){
        List<CareerEntity> careerList = careerDAO.findAll();
        careerList.forEach(System.out::println);
        assertNotNull(careerList);
    }

}
