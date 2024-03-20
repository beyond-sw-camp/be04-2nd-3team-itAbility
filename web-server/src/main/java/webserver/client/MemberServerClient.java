package webserver.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import webserver.member.vo.Mypage;
import webserver.member.vo.ResponseMypage;

@FeignClient(name="itability-member-service", url="localhost:8000")
public interface MemberServerClient {
    @GetMapping("/member-service/rest/mypage/{memberId}")
    public ResponseMypage getMember(@PathVariable long memberId);



}
