package com.team3.itability.mypage.dao;

import com.team3.itability.mypage.dto.ImageDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ImageDAOTest {


    @Autowired
    private ImageDAO imageDAO;

    @Test
    void name() {
        List<ImageDTO> imageList = imageDAO.findAll();
        imageList.forEach(System.out::println);
        assertNotNull(imageList);
    }
}