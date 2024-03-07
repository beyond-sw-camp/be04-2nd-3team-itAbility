package com.team3.itability.snsapi.naver.service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.team3.itability.member.dto.Provider;
import com.team3.itability.snsapi.naver.aggregate.NaverEntity;
import com.team3.itability.snsapi.naver.repository.NaverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

@Service
public class NaverServiceImpl implements NaverService {
    private final NaverRepository naverRepository;

    @Autowired
    public NaverServiceImpl(NaverRepository naverRepository) {
        this.naverRepository = naverRepository;
    }

    @Value("${naver.client-id}")
    private String clientId;

    @Value("${naver.client-secret}")
    private String clientSecret;

    @Value("${naver.redirect-uri}")
    private String redirectUri;

    // CSRF 방지를 위한 상태 토큰 생성
    @Override
    public String generateState() {
        SecureRandom random = new SecureRandom();
        return new BigInteger(130, random).toString(32);
    }

    // 네이버 액세스 토큰 받아오기
    @Override
    public String getToken(String code, String state) throws Exception {
        String requestUrl = "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code"
                + "&client_id=" + clientId
                + "&client_secret=" + clientSecret
                + "&redirect_uri=" + redirectUri
                + "&code=" + code
                + "&state=" + state;

        URL url = new URL(requestUrl);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String line;
        StringBuilder response = new StringBuilder();
        while ((line = br.readLine()) != null) {
            response.append(line);
        }
        br.close();

        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(response.toString());
        return element.getAsJsonObject().get("access_token").getAsString();
    }

    // 네이버 사용자 프로필 정보 조회
    @Override
    public Map<String, Object> getUserInfo(String accessToken) throws Exception {
        Map<String, Object> userInfoMap = new HashMap<>();
        final String requestUrl = "https://openapi.naver.com/v1/nid/me";

        URL url = new URL(requestUrl);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Authorization", "Bearer " + accessToken);

        String responseCode = String.valueOf(con.getResponseCode());
        BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = br.readLine()) != null) {
            response.append(inputLine);
        }
        br.close();

        // JSON 파싱
        JsonParser parser = new JsonParser();
        JsonObject jsonObject = parser.parse(response.toString()).getAsJsonObject();
        JsonObject responseObj = jsonObject.getAsJsonObject("response");

        String userId = responseObj.has("id") ? responseObj.get("id").getAsString() : null;
        String email = responseObj.has("email") ? responseObj.get("email").getAsString() : null;
        String name = responseObj.has("name") ? responseObj.get("name").getAsString() : null;
        String imgId = responseObj.has("profile_image") ? responseObj.get("profile_image").getAsString() : null;

        userInfoMap.put("userId", userId);
        userInfoMap.put("email", email);
        userInfoMap.put("name", name);
        userInfoMap.put("imgId", imgId);

        try {
            NaverEntity naverEntity = new NaverEntity(userId, imgId, email, name, Provider.NAVER);
            naverRepository.save(naverEntity);
            System.out.println("저장이 됨:  " + naverEntity);
        } catch (Exception e) {
            System.err.println("저장이 안됨: " + e.getMessage());
        }

        return userInfoMap;
    }
}
