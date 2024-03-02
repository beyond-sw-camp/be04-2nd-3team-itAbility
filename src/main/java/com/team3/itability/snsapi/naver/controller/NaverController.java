package com.team3.itability.snsapi.naver.controller;

import com.team3.itability.snsapi.naver.service.NaverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.Map;

public class NaverController {
    @Value("${naver.client-id}")
    private String apiKey;

    @Value("${naver.redirect-uri}")
    private String redirectUri;

    @Value("${naver.client-secret}")
    private String secretKey;

    private final NaverService naverService;

    public NaverController(NaverService naverService) {
        this.naverService = naverService;
    }


//    @GetMapping("naver")
//    public String naverConnect() {
//
//    }

}
