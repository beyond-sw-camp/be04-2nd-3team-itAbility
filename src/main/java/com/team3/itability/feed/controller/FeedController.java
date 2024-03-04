package com.team3.itability.feed.controller;

import com.team3.itability.feed.dto.FeedDTO;
import com.team3.itability.feed.service.FeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/feeds")
public class FeedController {

    @Autowired
    private FeedService feedService;

    @GetMapping("/listFeed")
    public ResponseEntity<List<FeedDTO>> getAllFeeds() {
        List<FeedDTO> feeds = feedService.findByFeeds();
        return ResponseEntity.ok().body(feeds);
    }
}
