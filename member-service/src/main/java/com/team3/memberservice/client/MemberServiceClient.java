package com.team3.memberservice.client;

import com.team3.memberservice.mypage.dto.ReqeuestRecruitCategory;
import com.team3.memberservice.mypage.dto.RequestRecruitCategory;
import com.team3.memberservice.mypage.dto.ResponseRecruitCategory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@FeignClient(name = "ITABILITY-BOARD-SERVICE", url = "localhost:8000") // gateway에 갈 클라이언트의 이름, gateway 주소
public interface MemberServiceClient{

    // gateway가 알고 있는 마이크로 서비스의 접두사(라우팅 시 설정한 요청 경로)를 추가하여 요청 경로를 작성한다.
    @GetMapping("/board-service/recruit/recruit-categories/{memberId}")
    List<ResponseRecruitCategory> getRecruitCategory(@PathVariable long memberId);

    @GetMapping("/board-service/recruit/recruit-category-list")
    List<ResponseRecruitCategory> getAllRecruitCategory();

    @PostMapping("/board-service/recruit/recruit-categories/{memberId}/{recruitId}")
    List<ResponseRecruitCategory> postRecruitCategory(@PathVariable long memberId, @PathVariable int recruitId);
    @DeleteMapping("/board-service/recruit/recruit-categories/{memberId}/{recruitId}")
    List<ResponseRecruitCategory> deleteRecruitCategory(@PathVariable long memberId, @PathVariable int recruitId);

}
