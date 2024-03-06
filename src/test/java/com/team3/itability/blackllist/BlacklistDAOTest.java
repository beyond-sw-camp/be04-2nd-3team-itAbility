package com.team3.itability.blackllist;

import com.team3.itability.blackllist.repository.BlacklistDAO;
import com.team3.itability.blackllist.dto.BlacklistDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class BlacklistDAOTest {

    @Autowired
    BlacklistDAO blacklistDAO;

    @Test
    void main() {
        List<BlacklistDTO> blacklist = blacklistDAO.findAll();
        blacklist.forEach(System.out::println);
        assertNotNull(blacklist);
    }
}