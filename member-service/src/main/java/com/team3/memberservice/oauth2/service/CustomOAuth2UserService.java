package com.team3.memberservice.oauth2.service;

import com.team3.memberservice.member.dao.MemberInfoRepo;
import com.team3.memberservice.member.dto.MemberInfoDTO;
import com.team3.memberservice.member.dto.UserDTO;
import com.team3.memberservice.oauth2.dto.CustomOAuth2User;
import com.team3.memberservice.snsapi.google.dto.GoogleResponse;
import com.team3.memberservice.snsapi.kakao.dto.KakaoResponse;
import com.team3.memberservice.snsapi.naver.dto.NaverResponse;
import com.team3.memberservice.oauth2.dto.OAuth2Response;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final MemberInfoRepo memberInfoRepo;

    public CustomOAuth2UserService(MemberInfoRepo memberInfoRepo) {
        this.memberInfoRepo = memberInfoRepo;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(userRequest);
        System.out.println(oAuth2User);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        OAuth2Response oAuth2Response = null;
        if (registrationId.equals("naver")) {

            oAuth2Response = new NaverResponse(oAuth2User.getAttributes());
        }
        else if (registrationId.equals("google")) {

            oAuth2Response = new GoogleResponse(oAuth2User.getAttributes());
        }
        else if (registrationId.equals("kakao")) {

            oAuth2Response = new KakaoResponse(oAuth2User.getAttributes());
        }
        else {

            return null;
        }
        String username = oAuth2Response.getProvider() + " " + oAuth2Response.getProviderId();
        System.out.println("username = " + username);
        MemberInfoDTO existData = memberInfoRepo.findByUsername(username);

        if (existData == null) {
            MemberInfoDTO userEntity = MemberInfoDTO.builder()
                    .username(username)
                    .email(oAuth2Response.getEmail())
                    .name(oAuth2Response.getName())
                    .provider(oAuth2Response.getProvider())
                    .picture(oAuth2Response.getThumbnail())
                    .build();

            memberInfoRepo.save(userEntity);


            UserDTO userDTO = UserDTO.builder()
                    .username(username)
                    .name(oAuth2Response.getName())
                    .role(oAuth2Response.getProvider())
                    .picture(oAuth2Response.getThumbnail())
                    .build();

            return new CustomOAuth2User(userDTO);
        } else {
            existData.setEmail(oAuth2Response.getEmail());
            existData.setName(oAuth2Response.getName());
            existData.setPicture(oAuth2Response.getThumbnail());

            memberInfoRepo.save(existData);

            UserDTO userDTO = UserDTO.builder()
                    .username(existData.getUsername())
                    .name(oAuth2Response.getName())
                    .role(existData.getProvider())
                    .picture(oAuth2Response.getThumbnail())
                    .build();

            return new CustomOAuth2User(userDTO);
        }

    }
}
