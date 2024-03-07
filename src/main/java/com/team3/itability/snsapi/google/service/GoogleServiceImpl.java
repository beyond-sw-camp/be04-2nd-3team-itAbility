package com.team3.itability.snsapi.google.service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.team3.itability.member.dao.MemberInfoRepo;
import com.team3.itability.member.dto.MemberInfoDTO;
import com.team3.itability.member.dto.Provider;
import com.team3.itability.mypage.dao.DegreeDAO;
import com.team3.itability.mypage.dao.ImageDAO;
import com.team3.itability.mypage.dao.MemberProfileDAO;
import com.team3.itability.mypage.entity.DegreeEntity;
import com.team3.itability.mypage.entity.ImageEntity;
import com.team3.itability.mypage.entity.MemberProfileEntity;
import com.team3.itability.mypage.enumData.IMG_USE;
import com.team3.itability.snsapi.google.repository.GoogleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

@Service
public class GoogleServiceImpl implements GoogleService {
    private final GoogleRepository googleRepository;
    private final MemberInfoRepo memberInfoRepo;
    private final MemberProfileDAO memberProfileDAO;
    private final DegreeDAO degreeDAO;
    private final ImageDAO imageDAO;

    @Autowired
    public GoogleServiceImpl(GoogleRepository googleRepository, MemberInfoRepo memberInfoRepo, MemberProfileDAO memberProfileDAO, DegreeDAO degreeDAO, ImageDAO imageDAO) {
        this.googleRepository = googleRepository;
        this.memberInfoRepo = memberInfoRepo;
        this.memberProfileDAO = memberProfileDAO;
        this.degreeDAO = degreeDAO;
        this.imageDAO = imageDAO;
    }

    @Override
    public String getToken(String code) throws Exception {
        String access_Token = "";
        // 올바른 엔드포인트로 수정합니다.
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
        sb.append("&client_secret=").append(URLEncoder.encode("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", "UTF-8")); // 클라이언트 시크릿 추가
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
            System.out.println("토큰 못가져왔어요...");
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

        if(!memberInfoRepo.existsById(userId)) {
            MemberInfoDTO member = new MemberInfoDTO(userId,name,email,Provider.GOOGLE);
            ImageEntity imageEntity = new ImageEntity(userId, profilePicture, IMG_USE.profile, "link");
            imageDAO.save(imageEntity);
            MemberProfileEntity profile = new MemberProfileEntity(member,member.getName(), imageEntity);
            DegreeEntity degree = new DegreeEntity();
            degreeDAO.save(degree);
            profile.setDegree(degree);
            memberProfileDAO.save(profile);
        }

        // 정보 출력 (디버깅 목적)
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


