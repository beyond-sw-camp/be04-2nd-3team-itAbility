package com.team3.itability.mypage.dao;

import com.team3.itability.img.dao.ImageDAO;
import com.team3.itability.img.entity.ImageEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ImageDTODAOTest {


    @Autowired
    private ImageDAO imageDAO;

    @Test
    void name() {
        List<ImageEntity> imageList = imageDAO.findAll();
        imageList.forEach(System.out::println);
        assertNotNull(imageList);
    }
}