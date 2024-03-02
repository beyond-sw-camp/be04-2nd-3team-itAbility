package com.team3.itability.mypage.service;

import com.team3.itability.mypage.dao.*;
import com.team3.itability.mypage.dto.DegreeDTO;
import com.team3.itability.mypage.dto.MemberProfileDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service

public class MypageService {
    MemberProfileDAO memberProfileDAO;
    DegreeDAO degreeDAO;
    ImageDAO imageDAO;
    CareerDAO careerDAO;
    MemberSkillDAO memberSkillDAO;

    @Autowired
    public MypageService(MemberProfileDAO memberProfileDAO, DegreeDAO degreeDAO, ImageDAO imageDAO, CareerDAO careerDAO, MemberSkillDAO memberSkillDAO) {
        this.memberProfileDAO = memberProfileDAO;
        this.degreeDAO = degreeDAO;
        this.imageDAO = imageDAO;
        this.careerDAO = careerDAO;
        this.memberSkillDAO = memberSkillDAO;
    }



    /*1. 자신의 마이페이지 정보 조회*/
    public MemberProfileDTO printMypageData(int memberCode){
        MemberProfileDTO memberProfileDTO =memberProfileDAO.findById(memberCode).orElseThrow();
        System.out.println("memberProfileDTO = " + memberProfileDTO);
        return memberProfileDTO;
    }

    /*2. 마이페이지 수정*/
    @Transactional
    public MemberProfileDTO modifyMypage(int memberCode, String nickname, String name) {
        MemberProfileDTO memberProfileDTO = memberProfileDAO.findById(memberCode).orElseThrow();
        memberProfileDTO.setNickname(nickname);
        memberProfileDTO.getMemberInfo().setName(name);
        return memberProfileDTO;
    }

    @Transactional
    public MemberProfileDTO modifyDegree(int memberId, DegreeDTO degreeDTO) {

        MemberProfileDTO memberProfileDTO = memberProfileDAO.findById(memberId).orElseThrow();
        memberProfileDTO.getDegree().setMajor(degreeDTO.getMajor());
        memberProfileDTO.getDegree().setEnrollDate(degreeDTO.getEnrollDate());
        memberProfileDTO.getDegree().setGraduateDate(degreeDTO.getGraduateDate());
        memberProfileDTO.getDegree().setFinalEduName(degreeDTO.getFinalEduName());
        memberProfileDTO.getDegree().setRegisterStatus(degreeDTO.isRegisterStatus());
        System.out.println("memberProfileDTO = " + memberProfileDTO.getDegree());
        /*egree=DegreeDTO{degreeId=1, finalEduName='서울대학교', enrollDate='2010-03-01', graduateDate='2014-02-28', major='컴퓨터 공학', registerStatus=false*/
        return memberProfileDTO;
    }
}
