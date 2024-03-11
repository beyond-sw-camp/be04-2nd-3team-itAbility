package com.team3.itability.snsapi.naver.service;

import java.util.ArrayList;
import java.util.Map;

public interface NaverService {

    // CSRF 방지를 위한 상태 토큰 생성
    String generateState();

    // 네이버 액세스 토큰 받아오기
    String getToken(String code, String state) throws Exception;

    // 네이버 사용자 프로필 정보 조회
    Map<String, Object> getUserInfo(String accessToken) throws Exception;

}
