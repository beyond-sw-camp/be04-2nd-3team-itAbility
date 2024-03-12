package com.team3.memberservice.snsapi.google.service;

import java.util.ArrayList;

public interface GoogleService {
    String getToken(String code) throws Exception;
    ArrayList<Object> getUserInfo(String access_token) throws Exception;
}
