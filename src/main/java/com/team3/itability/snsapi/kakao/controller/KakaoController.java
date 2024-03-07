package com.team3.itability.snsapi.kakao.controller;

import com.team3.itability.snsapi.kakao.service.KakaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;

@RestController
@RequestMapping("/")
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

    @GetMapping("/kakao")
    public RedirectView kakaoConnect() {
        String url = UriComponentsBuilder.fromHttpUrl("https://kauth.kakao.com/oauth/authorize")
                .queryParam("client_id", apiKey)
                .queryParam("redirect_uri", redirectUri)
                .queryParam("response_type", "code")
                .toUriString();

        return new RedirectView(url);
    }

    @GetMapping("/login/kakao")
    public ModelAndView kakaoLogin(@RequestParam("code") String code) throws Exception {
        // code로 토큰 받음
        String access_token = kakaoService.getToken(code);

        // 토큰으로 사용자 정보 담은 list 가져오기
        ArrayList<Object> list = kakaoService.getUserInfo(access_token);

        // list 모델에 담아 view로 넘김
        ModelAndView modelAndView = new ModelAndView("KakaoUserInfo");
        modelAndView.addObject("list", list);
        return modelAndView;
    }
}
