package com.team3.boardservice.search.controller;

import co.elastic.clients.elasticsearch.ml.Job;
import com.team3.boardservice.search.entity.BoardIndex;
import com.team3.boardservice.search.entity.CommentIndex;
import com.team3.boardservice.search.entity.RecruitIndex;
import com.team3.boardservice.search.entity.SearchItem;
import com.team3.boardservice.search.service.SearchItemService;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

// SearchController.java 수정 버전

@RestController
@RequestMapping("/api/search")
public class SearchController {
    private final SearchItemService service;

    private final ElasticsearchOperations elasticsearchOperations;

    // 생성자를 통해 SearchItemService 인스턴스를 주입받음
    public SearchController(SearchItemService service, ElasticsearchOperations elasticsearchOperations) {
        this.service = service;
        this.elasticsearchOperations = elasticsearchOperations;
    }

    @GetMapping("/job")
    public ResponseEntity<?> searchJob(@RequestParam String jobQuery) {
        try {
            System.out.println("jobQuery = " + jobQuery);
            List<SearchItem> jobs = service.searchJob(jobQuery);
            return new ResponseEntity<>(jobs, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/board")
    public ResponseEntity<?> searchBoard(@RequestParam String boardQuery) {
        try {
            List<BoardIndex> boards = service.searchBoard(boardQuery);
            return new ResponseEntity<>(boards, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // 채용 정보 검색 엔드포인트
    @GetMapping("/recruit")
    public ResponseEntity<?> searchRecruit(@RequestParam String recruitQuery) {
        try {
            return new ResponseEntity<>(service.searchRecruit(recruitQuery), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // 댓글 검색 엔드포인트
    @GetMapping("/comment")
    public ResponseEntity<?> searchComment(@RequestParam String commentQuery) {
        try {
            return new ResponseEntity<>(service.searchComment(commentQuery), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}