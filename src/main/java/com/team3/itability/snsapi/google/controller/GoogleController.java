package com.team3.itability.snsapi.google.controller;

import com.team3.itability.snsapi.google.service.GoogleService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
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
public class GoogleController {
    private final GoogleService googleService;

    @Autowired
    public GoogleController(GoogleService googleService) {
        this.googleService = googleService;
    }

    @GetMapping("google")
    public RedirectView googleConnect(HttpSession session) {
        String url = UriComponentsBuilder.fromHttpUrl("https://accounts.google.com/o/oauth2/auth")
                .queryParam("client_id", "529642165041-sv8fd7c0sb7to66f25t1h98av3qhmomv.apps.googleusercontent.com")
                .queryParam("redirect_uri", "http://localhost:8080/login/google")
                .queryParam("response_type", "code")
                .queryParam("scope", "email profile openid")
                .toUriString();

        return new RedirectView(url);
    }


    @RequestMapping(value = "login/google")
    public ModelAndView googleLogin(@RequestParam(value = "code") String code) throws Exception {
        // code로 토큰 받음
        String access_token = googleService.getToken(code);

        // 토큰으로 사용자 정보 담은 list 가져오기
        ArrayList<Object> list = googleService.getUserInfo(access_token);

        // list 모델에 담아 view로 넘김
        ModelAndView modelAndView = new ModelAndView("googleUserInfo");
        modelAndView.addObject("list", list);
        return modelAndView;
    }

}
