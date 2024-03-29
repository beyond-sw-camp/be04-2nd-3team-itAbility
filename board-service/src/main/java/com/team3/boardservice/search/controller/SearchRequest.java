package com.team3.boardservice.search.controller;

import com.team3.boardservice.search.entity.SearchItem;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

public class SearchRequest {



    private String jobQuery;


    // getter와 setter
    public String getJobQuery() {
        return jobQuery;
    }

    public void setJobQuery(String jobQuery) {
        this.jobQuery = jobQuery;
    }
}

// 컨트롤러 메서드
