package com.team3.itability.snsapi.kakao.controller;

import com.team3.itability.snsapi.kakao.service.KakaoService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
public class KakaoController {

    private final KakaoService kakaoService;

    @Autowired
    public KakaoController(KakaoService kakaoService) {
        this.kakaoService = kakaoService;
    }

    @Value("${kakao.client-id}")
    private String apiKey;

    @Value("${kakao.redirect-uri}")
    private String redirectUri;

    @GetMapping("kakao")
    public String kakaoConnect() {

        StringBuffer url = new StringBuffer();
        url.append("https://kauth.kakao.com/oauth/authorize?");
        url.append("client_id=" + apiKey);
        url.append("&redirect_uri=" + redirectUri);
        url.append("&response_type=code");

        return "redirect:" + url.toString();
    }

    @RequestMapping(value = "login/kakao")
    public String kakaoLogin(@RequestParam("code") String code, Model model , HttpSession session) throws Exception {

        //code로 토큰 받음
        String access_token = kakaoService.getToken(code);

        //토큰으로 사용자 정보 담은 list 가져오기
        ArrayList<Object> list = kakaoService.getUserInfo(access_token);

        //list 모델에 담아 view로 넘김
        model.addAttribute("list", list);

        return "userInfo";
    }

}
