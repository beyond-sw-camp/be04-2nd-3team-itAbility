package com.team3.itability.mypage.dao;

import com.team3.itability.mypage.dto.CareerDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CareerDTOTest {
    @Autowired
    CareerDAO careerDAO;

    @Test
    void name(){
        List<CareerDTO> careerList = careerDAO.findAll();
        careerList.forEach(System.out::println);
        assertNotNull(careerList);
    }

}
