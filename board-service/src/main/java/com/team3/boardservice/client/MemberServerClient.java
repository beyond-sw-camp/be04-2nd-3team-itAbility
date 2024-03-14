package com.team3.boardservice.client;

import com.team3.boardservice.member.dto.ResponseMemberInfo;
import com.team3.boardservice.mypage.entity.SkillEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "ITABILITY-MEMBER-SERVICE", url = "localhost:8282")
public interface MemberServerClient {

//    1. MemberInfoDTO, MemberInfoRepo
    @GetMapping("/member/{memberId}")
    public ResponseMemberInfo getMember(@PathVariable long memberId);

    @GetMapping("/members")
    public List<ResponseMemberInfo> getAllMember();

    @GetMapping("member-service/rest/mypage/skill/{skillId}")
    public SkillEntity getSkill(@PathVariable int skillId);

}
