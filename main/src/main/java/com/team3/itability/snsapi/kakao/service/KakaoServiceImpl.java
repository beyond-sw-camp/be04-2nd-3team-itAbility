package com.team3.itability.snsapi.kakao.service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.team3.itability.snsapi.common.CommonService;
import com.team3.itability.snsapi.kakao.repository.KakaoRepository;
import com.team3.itability.snsapi.kakao.aggregate.Kakaouser;
import com.team3.itability.member.dto.Provider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

@Service
@Transactional
public class KakaoServiceImpl implements KakaoService {

    private final KakaoRepository kakaoRepository;
    private final CommonService commonService;
    @Autowired
    public KakaoServiceImpl(KakaoRepository kakaoRepository, CommonService commonService) {
        this.kakaoRepository = kakaoRepository;
        this.commonService = commonService;
    }

    @Value("${kakao.client-id}")
    private String apiKey;

    @Value("${kakao.redirect-uri}")
    private String redirectUri;

    @Override
    public String getToken(String code) throws Exception {
        String access_Token = "";

        //EndPoint URL = API가 서버에서 자원에 접근할 수 있도록 하는 URL
        final String requestUrl = "https://kauth.kakao.com/oauth/token";

        //토큰을 요청할 URL 객체 생성
        URL url = new URL(requestUrl);

        //HTTP 연결 설정
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setDoOutput(true);

        //서버로 요청 보내기
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(con.getOutputStream()));
        String sb = "grant_type=authorization_code" +
                "&client_id=" + apiKey +
                "&redirect_uri=" + redirectUri +
                "&code=" + code;
        bw.write(sb);
        bw.flush();

        //서버의 응답 데이터 가져옴
        BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String line = "";
        String result = "";

        //result에 토큰이 포함된 응답데이터를 한줄씩 저장
        while ((line = br.readLine()) != null) {
            result += line;
        }

        //JSON 데이터를 파싱하기 위한 JsonParser
        JsonParser parser = new JsonParser();
        //값 추출을 위해 파싱한 데이터를 JsonElement로 변환
        JsonElement element = parser.parse(result);

        //element에서 access_token 값을 얻어옴
        access_Token = element.getAsJsonObject().get("access_token").getAsString();

        br.close();
        bw.close();

        return access_Token;

    }

    @Override
    public ArrayList<Object> getUserInfo(String access_token) throws Exception {

        ArrayList<Object> list = new ArrayList<Object>();

        final String requestUrl = "https://kapi.kakao.com/v2/user/me";

        URL url = new URL(requestUrl);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Authorization", "Bearer " + access_token);

        BufferedReader bf = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String line = "";
        String result = "";

        while ((line = bf.readLine()) != null) {
            result += line;
        }

        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(result);

        JsonObject properties = element.getAsJsonObject().get("properties").getAsJsonObject();
        JsonObject kakao_account = element.getAsJsonObject().get("kakao_account").getAsJsonObject();
        JsonObject id = element.getAsJsonObject();

        //콘솔창 확인 후 필요한 정보 추출
        System.out.println("----------properties" + properties);
        System.out.println("----------kakao_account" + kakao_account);

        Long userId = id.get("id").getAsLong();
        String imgId = properties.getAsJsonObject().get("thumbnail_image").getAsString();
        String name = properties.getAsJsonObject().get("nickname").getAsString();
        String email = kakao_account.getAsJsonObject().get("email").getAsString();

        list.add(userId);
        list.add(imgId);
        list.add(name);
        list.add(email);


        //DB 저장
        Kakaouser kakaoouser = new Kakaouser(userId, imgId, email, name, Provider.KAKAO);

        if(commonService.existMember(userId)==0){
            commonService.addUserLogin(userId,imgId,name,email,Provider.KAKAO);
        }

        kakaoRepository.save(kakaoouser);
        System.out.println("kakaouser = " + kakaoouser);

        return list;
    }

}
