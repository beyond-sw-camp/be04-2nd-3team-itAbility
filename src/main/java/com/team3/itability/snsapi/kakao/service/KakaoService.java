package com.team3.itability.snsapi.kakao.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public interface KakaoService {

    public String getToken(String code) throws Exception ;
    public ArrayList<Object> getUserInfo(String access_token) throws Exception;

}
