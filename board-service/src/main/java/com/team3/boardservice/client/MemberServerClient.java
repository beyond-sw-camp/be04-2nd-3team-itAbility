package com.team3.boardservice.client;


import com.team3.boardservice.member.dto.ResponseMember;
import com.team3.boardservice.recruitment.vo.ResponseSkill;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name="itability-member-service", url="localhost:8000")
public interface MemberServerClient {

    @GetMapping("/member-service/info/member/{memberId}")
    public ResponseMember getMember(@PathVariable long memberId);

    @GetMapping("/member-service/info/members")
    public List<ResponseMember> getAllMember();

    @GetMapping("member-service/rest/mypage/skill/{skillId}")
    public ResponseSkill getSkill(@PathVariable int skillId);

    @GetMapping("member-service/rest/mypage/skill/list")
    public List<ResponseSkill> getAllSkill();
}


