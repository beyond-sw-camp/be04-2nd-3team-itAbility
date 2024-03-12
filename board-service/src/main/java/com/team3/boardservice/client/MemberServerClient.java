package com.team3.boardservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "ITABILITY-MEMBER-SERVICE", url = "localhost:8282")
public interface MemberServerClient {

//    1. MemberInfoDTO, MemberInfoRepo



}
