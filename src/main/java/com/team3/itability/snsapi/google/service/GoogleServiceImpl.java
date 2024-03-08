package com.team3.itability.snsapi.google.service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.team3.itability.member.dto.Provider;
import com.team3.itability.snsapi.common.CommonService;
import com.team3.itability.snsapi.google.aggregate.GoogleEntity;
import com.team3.itability.snsapi.google.repository.GoogleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

@Service
public class GoogleServiceImpl implements GoogleService {

    @Value("${google.client-id}")
    private String clientId;

    @Value("${google.client-secret}")
    private String clientSecret;

    @Value("${google.redirect-uri}")
    private String redirectUri;

    private final GoogleRepository googleRepository;
    private final CommonService commonService;
    @Autowired
    public GoogleServiceImpl(GoogleRepository googleRepository, CommonService commonService) {
        this.googleRepository = googleRepository;
        this.commonService = commonService;
    }

    @Override
    public String getToken(String code) throws Exception {
        String access_Token = "";
        final String requestUrl = "https://oauth2.googleapis.com/token";

        // HTTP 연결 설정
        URL url = new URL(requestUrl);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setDoOutput(true);
        // Content-Type 설정
        con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

        // 서버로 요청을 보낼 파라미터 구성
        StringBuilder sb = new StringBuilder();
        sb.append("code=").append(URLEncoder.encode(code, "UTF-8"));
        sb.append("&client_id=").append(URLEncoder.encode("529642165041-sv8fd7c0sb7to66f25t1h98av3qhmomv.apps.googleusercontent.com", "UTF-8"));
        sb.append("&client_secret=").append(URLEncoder.encode("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", "UTF-8"));
        sb.append("&redirect_uri=").append(URLEncoder.encode("http://localhost:8080/login/google", "UTF-8"));
        sb.append("&grant_type=authorization_code");

        // 서버로 요청 보내기
        try (OutputStream os = con.getOutputStream()) {
            byte[] input = sb.toString().getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        // 서버의 응답 데이터 가져옴
        StringBuilder response = new StringBuilder();
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(con.getInputStream(), "utf-8"))) {
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
        }

        // JSON 데이터 파싱
        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(response.toString());

        // access_token 값을 얻어옴
        if (element.getAsJsonObject().has("access_token")) {
            access_Token = element.getAsJsonObject().get("access_token").getAsString();
            // 토큰 값을 콘솔에 출력
            System.out.println("Access Token: " + access_Token);
        } else {
            // 토큰을 얻지 못했을 경우의 처리
            System.out.println("토큰 못가져옴...");
        }


        return access_Token;
    }

    @Override
    public ArrayList<Object> getUserInfo(String access_token) throws Exception {
        ArrayList<Object> userInfoList = new ArrayList<>();

        final String requestUrl = "https://www.googleapis.com/oauth2/v2/userinfo";

        URL url = new URL(requestUrl);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Authorization", "Bearer " + access_token);

        BufferedReader bf = new BufferedReader(new InputStreamReader(con.getInputStream()));
        StringBuilder result = new StringBuilder();
        String line;
        while ((line = bf.readLine()) != null) {
            result.append(line);
        }
        bf.close();

        // JSON 데이터를 파싱
        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(result.toString());
        JsonObject jsonObject = element.getAsJsonObject();

        // 필요한 정보 추출
        String id = jsonObject.has("id") ? jsonObject.get("id").getAsString() : null;
        String email = jsonObject.has("email") ? jsonObject.get("email").getAsString() : null;
        String name = jsonObject.has("name") ? jsonObject.get("name").getAsString() : null;
        String profilePicture = jsonObject.has("picture") ? jsonObject.get("picture").getAsString() : null;
        Long userId;
        String first12Digits = id.substring(0, 12);
        userId = Long.valueOf(first12Digits);

        try {
            GoogleEntity googleEntity = new GoogleEntity(userId, email, name, profilePicture, Provider.GOOGLE);
            googleRepository.save(googleEntity);
            System.out.println("저장이 됨: " + googleEntity);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("저장이 안됨: " + e.getMessage());
        }

        if(commonService.existMember(userId)==0){
            commonService.addUserLogin(userId,profilePicture,name,email,Provider.GOOGLE);
        }


        System.out.println("ID: " + id);
        System.out.println("Email: " + email);
        System.out.println("Name: " + name);
        System.out.println("Profile Picture: " + profilePicture);

        // 추출한 정보를 리스트에 추가
        userInfoList.add(id);
        userInfoList.add(email);
        userInfoList.add(name);
        userInfoList.add(profilePicture);

        return userInfoList;
    }

}


