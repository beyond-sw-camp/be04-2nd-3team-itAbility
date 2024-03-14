package com.team3.boardservice.client;


import com.team3.boardservice.member.dto.MemberInfoDTO;
import com.team3.boardservice.mypage.entity.SkillEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "ITABILITY-MEMBER-SERVICE", url = "localhost:8000")
public interface MemberServerClient {

//    1. MemberInfoDTO, MemberInfoRepo
        ///order-service/orders/users/{userId}
    @GetMapping("member-service/info/member/{memberId}")
    public MemberInfoDTO getMember(@PathVariable long memberId);

    @GetMapping("member-service/info/members")
    public List<MemberInfoDTO> getAllMember();

    @GetMapping("member-service/rest/mypage/skill/{skillId}")
    public SkillEntity getSkill(@PathVariable int skillId);

    @GetMapping("member-service/rest/mypage/skill/list")
    public List<SkillEntity> getAllSkill();
}
