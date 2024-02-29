package com.team3.itability.follow2user;

import com.team3.itability.follow2user.dao.FollowDAO;
import com.team3.itability.follow2user.dto.FollowDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FollowDAOTest {

    @Autowired
    private FollowDAO followDAO;

    @Test
    void main(){
        List<FollowDTO> followList = followDAO.findAll();
        followList.forEach(System.out::println);
        assertNotNull(followList);
    }

}