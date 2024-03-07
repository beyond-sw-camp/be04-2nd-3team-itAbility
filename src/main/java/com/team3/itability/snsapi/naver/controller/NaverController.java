package com.team3.itability.snsapi.naver.controller;

import com.team3.itability.snsapi.naver.service.NaverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Map;

@RestController
@RequestMapping("/")
public class NaverController {
    private final NaverService naverService;

    @Autowired
    public NaverController(NaverService naverService) {
        this.naverService = naverService;
    }

    @Value("${naver.client-id}")
    private String clientId;


    @Value("${naver.redirect-uri}")
    private String redirectUri;

    @GetMapping("/naver")
    public RedirectView naverConnect() {
        String state = naverService.generateState();
        String url = "https://nid.naver.com/oauth2.0/authorize?response_type=code"
                + "&client_id=" + clientId
                + "&redirect_uri=" + redirectUri
                + "&state=" + state;
        return new RedirectView(url);
    }

    @GetMapping("/login/naver")
    public ModelAndView naverLogin(@RequestParam("code") String code, @RequestParam("state") String state) throws Exception {
        String accessToken = naverService.getToken(code, state);
        Map<String, Object> userInfo = naverService.getUserInfo(accessToken);
        ModelAndView mav = new ModelAndView("NaverUserInfo");
        mav.addObject("userInfo", userInfo);
        return mav;
    }
}
