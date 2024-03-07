package com.team3.itability.feed;



import com.team3.itability.feed.dto.FeedDTO;
import com.team3.itability.feed.dto.ImgDTO;
import com.team3.itability.feed.dto.LikeDTO;
import com.team3.itability.reple.repository.CommentRepo;
import com.team3.itability.feed.repository.FeedRepo;
import com.team3.itability.feed.repository.ImgRepo;
import com.team3.itability.feed.repository.LikeRepo;
import com.team3.itability.reple.aggregate.CommentEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class FeedDAOTest {

    @Autowired
    FeedRepo feedRepo;

    @Autowired
    LikeRepo likeRepo;

    @Autowired
    CommentRepo commentRepo;

    @Autowired
    ImgRepo imgRepo;

    @Test
    void name() {
        List<FeedDTO> memoList = feedRepo.findAll();
        memoList.forEach(System.out::println);
        assertNotNull(memoList);
    }

    @Test
    void name1() {
        List<LikeDTO> memoList = likeRepo.findAll();
        memoList.forEach(System.out::println);
        assertNotNull(memoList);
    }

    @Test
    void name2() {
        List<CommentEntity> memoList = commentRepo.findAll();
        memoList.forEach(System.out::println);
        assertNotNull(memoList);
    }

    @Test
    void name3() {
        List<ImgDTO> memoList = imgRepo.findAll();
        memoList.forEach(System.out::println);
        assertNotNull(memoList);
    }
}
