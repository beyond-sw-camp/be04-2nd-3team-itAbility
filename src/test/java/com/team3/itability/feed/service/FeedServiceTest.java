package com.team3.itability.feed.service;

import com.team3.itability.reple.aggregate.CommentEntity;
import com.team3.itability.reple.dto.CommentDTO;
import com.team3.itability.reple.repository.CommentRepo;
import com.team3.itability.reple.service.CommentService;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;


import com.team3.itability.feed.repository.FeedRepo;
import com.team3.itability.feed.dto.FeedDTO;
import com.team3.itability.member.dao.MemberInfoRepo;
import com.team3.itability.member.dto.MemberInfoDTO;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


import java.util.List;
import java.util.Optional;


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

    @Autowired
    private CommentService commentService;

    @MockBean
    private CommentRepo commentRepo;

    @MockBean
    private FeedRepo feedRepo1;

    @MockBean
    private MemberInfoRepo memberInfoRepo1;


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



    /* 댓글 생성 테스트 */
//    @Test
//    void name10() {
//        CommentDTO commentDTO = new CommentDTO();
//        commentDTO.setBoardId(new FeedDTO());
//        commentDTO.setCmtContent("집에 보내줘");
//        commentDTO.setCmtId(11);
//        commentDTO.setReportCount(0);
//        commentDTO.setWriteDate("2024");
//        MemberInfoDTO memberInfoDTO = memberInfoRepo.findById(3L).orElseThrow();
//        commentDTO.setMemberId(memberInfoDTO);
//        CommentEntity CommentDTO;
//        commentRepo.save(CommentDTO);
//    }

}