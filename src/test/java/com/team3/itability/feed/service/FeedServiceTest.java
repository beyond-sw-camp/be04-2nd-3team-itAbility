package com.team3.itability.feed.service;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;


import com.team3.itability.feed.dao.FeedRepo;
import com.team3.itability.feed.dto.FeedDTO;
import com.team3.itability.member.dao.MemberInfoRepo;
import com.team3.itability.member.dto.MemberInfoDTO;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;


import java.util.List;



@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
class FeedServiceTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private FeedRepo feedRepo;

    @Autowired
    private MemberInfoRepo memberInfoRepo;


    /* 게시물 전체 조회 테스트 */
    @Test
    void name() {
        List<FeedDTO> findByFeeds = feedRepo.findAll();
        System.out.println("findByFeeds = " + findByFeeds);
    }

    /* 게시물 상세 조회 테스트 */
    // postman에서 테스트 완료


    /* 게시물 생성 테스트 */
    @Test
    void name1() {
        FeedDTO feedDTO = new FeedDTO();
        feedDTO.setHits(0);
        feedDTO.setBoardContent("집에 보내줘");
        feedDTO.setImgId(null);
        feedDTO.setBoardTitile("집에 보내주세요");
        feedDTO.setReportCount(0);
        feedDTO.setWriteDate("2024");
        feedDTO.setIsActive(0);
        MemberInfoDTO memberInfoDTO = memberInfoRepo.findById(3L).orElseThrow();
        feedDTO.setMemberId(memberInfoDTO);
        feedRepo.save(feedDTO);
        }

    /* 게시물 수정 테스트 */



    /* 게시물 삭제 테스트 */



}