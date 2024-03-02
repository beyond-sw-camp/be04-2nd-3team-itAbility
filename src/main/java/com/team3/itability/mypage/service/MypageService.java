package com.team3.itability.mypage.service;

import com.team3.itability.mypage.dao.MemberProfileDAO;
import com.team3.itability.mypage.dto.MemberProfileDTO;

public class MypageService {
    MemberProfileDAO memberProfileDAO;

    /*1. 자신의 마이페이지 정보 조회*/
    void printMypageData(int memberId){
        MemberProfileDTO memberProfileDTO =memberProfileDAO.findById(memberId).orElseThrow();
        System.out.println("memberProfileDTO = " + memberProfileDTO);
    }
}
