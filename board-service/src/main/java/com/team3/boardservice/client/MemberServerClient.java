package com.team3.boardservice.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "ITABILITY-MEMBER-SERVICE", url = "localhost:8282")
public interface MemberServerClient {

//    1. MemberInfoDTO, MemberInfoRepo

    @GetMapping("member/{memberId}")
    public MemberInfoDTO getMember(@PathVariable long memberId);

    @GetMapping("/members")
    public List<MemberInfoDTO> getAllMember();









}
