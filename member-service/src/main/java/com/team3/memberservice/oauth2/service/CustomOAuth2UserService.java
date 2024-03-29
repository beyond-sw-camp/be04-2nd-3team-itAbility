package com.team3.memberservice.oauth2.service;

import com.team3.memberservice.member.dao.MemberInfoRepo;
import com.team3.memberservice.member.dto.MemberInfoDTO;
import com.team3.memberservice.member.dto.UserDTO;
import com.team3.memberservice.oauth2.dto.CustomOAuth2User;
import com.team3.memberservice.snsapi.common.CommonService;
import com.team3.memberservice.snsapi.github.GithubResponse;
import com.team3.memberservice.snsapi.google.dto.GoogleResponse;
import com.team3.memberservice.snsapi.kakao.dto.KakaoResponse;
import com.team3.memberservice.snsapi.naver.dto.NaverResponse;
import com.team3.memberservice.oauth2.dto.OAuth2Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@Service
@Transactional
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final MemberInfoRepo memberInfoRepo;

    private final CommonService commonService;

    @Autowired
    public CustomOAuth2UserService(MemberInfoRepo memberInfoRepo, CommonService commonService) {
        this.memberInfoRepo = memberInfoRepo;
        this.commonService = commonService;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(userRequest);
        System.out.println(oAuth2User);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        OAuth2Response oAuth2Response = null;
        if (registrationId.equals("naver")) {

            oAuth2Response = new NaverResponse(oAuth2User.getAttributes());

            MessageDigest md = null;
            try {
                md = MessageDigest.getInstance("SHA-512");
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
            byte[] digest = md.digest(oAuth2Response.getProviderId().getBytes());
            long providerId = new BigInteger(digest).longValue();
            System.out.println("sha256: " +providerId );
        }
        else if (registrationId.equals("google")) {

            oAuth2Response = new GoogleResponse(oAuth2User.getAttributes());


        }
        else if (registrationId.equals("kakao")) {

            oAuth2Response = new KakaoResponse(oAuth2User.getAttributes());
        }

        else if (registrationId.equals("github")) {

            oAuth2Response = new GithubResponse(oAuth2User.getAttributes());
        }
        else {

            return null;
        }

        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        byte[] digest = md.digest(oAuth2Response.getProviderId().getBytes());
        long providerId;
        if(registrationId.equals("naver")||registrationId.equals("google")){

            providerId = new BigInteger(digest).longValue();
            if (providerId < 0) {
                providerId = -providerId;
            }
        } else  {
            providerId = Long.parseLong(oAuth2Response.getProviderId());

        }


        System.out.println("sha512: " + providerId );


        String username = oAuth2Response.getProvider() + " " + oAuth2Response.getProviderId();
        System.out.println("username = " + username);

        MemberInfoDTO existData = memberInfoRepo.findByUsername(username);

        if (existData == null) {
            MemberInfoDTO userEntity = MemberInfoDTO.builder()
                    .memberId(providerId) // 변환된 long 타입의 providerId 사용
                    .username(username)
                    .email(oAuth2Response.getEmail())
                    .name(oAuth2Response.getName())
                    .role(oAuth2Response.getProvider()) // 역할 설정 수정이 필요할 수 있습니다
                    .picture(oAuth2Response.getThumbnail())
                    .build();



            userEntity = memberInfoRepo.save(userEntity);
            commonService.addUserLogin2(userEntity);

            UserDTO userDTO = UserDTO.builder()
                    .email(oAuth2Response.getEmail())
                    .username(username)
                    .name(oAuth2Response.getName())
                    .role(oAuth2Response.getProvider()) // 역할 설정 수정이 필요할 수 있습니다
                    .picture(oAuth2Response.getThumbnail())
                    .build();

            return new CustomOAuth2User(userDTO);
        } else {
            existData.setEmail(oAuth2Response.getEmail());
            existData.setName(oAuth2Response.getName());
            existData.setPicture(oAuth2Response.getThumbnail());

            memberInfoRepo.save(existData);

            UserDTO userDTO = UserDTO.builder()
                    .email(existData.getEmail())
                    .username(existData.getUsername())
                    .name(oAuth2Response.getName())
                    .role(existData.getRole()) // 역할 설정 확인
                    .picture(oAuth2Response.getThumbnail())
                    .build();

            return new CustomOAuth2User(userDTO);
        }

    }
}