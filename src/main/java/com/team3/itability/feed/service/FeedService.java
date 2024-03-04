package com.team3.itability.feed.service;

import com.team3.itability.feed.dao.FeedRepo;
import com.team3.itability.feed.dto.FeedDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FeedService {

    @Autowired
    private FeedRepo feedRepo;

    /* 게시물 전체 조회 */
    @Transactional(readOnly = true)
    public List<FeedDTO> findByFeeds() {
        List<FeedDTO> feedList = feedRepo.findAll();
        System.out.println("feedList = " + feedList);
        return feedRepo.findAll();
    }

}
