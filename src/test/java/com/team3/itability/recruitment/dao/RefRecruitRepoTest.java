package com.team3.itability.recruitment.dao;

import com.team3.itability.recruitment.dto.RefRecruitCategoryDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RefRecruitRepoTest {

    @Autowired
    private RefRecruitRepo refRecDAO;

    @Test
    void main(){
        List<RefRecruitCategoryDTO> refList = refRecDAO.findAll();
        refList.forEach(System.out::println);
        assertNotNull(refList);
    }

}