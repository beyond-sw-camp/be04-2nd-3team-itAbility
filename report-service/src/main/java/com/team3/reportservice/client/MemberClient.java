package com.team3.reportservice.client;

import com.team3.reportservice.report.aggregate.Member;
import com.team3.reportservice.vo.ResponseMember;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;


@FeignClient(name="itability-member-service", url="localhost:8000")
public interface MemberClient {

    /* 설명. gateway가 알고있는 마이크로 서비스의 접두사(라우팅 시 설정한 요청 경로)를 추가해서 요청경로를 작성한다. */
    @GetMapping("/member-service/info/member/{memberId}")
    ResponseMember getMember(@PathVariable("memberId") long memberId);

    @GetMapping("/member-service/info/members")
    List<ResponseMember> getAllMember();

    @GetMapping("/member-service/info/member/{memberId}/report")
    ResponseEntity<String> reportMember(@PathVariable long memberId);
}
