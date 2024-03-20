package webserver.member.service;

import org.springframework.stereotype.Service;
import webserver.client.MemberServerClient;
import webserver.member.vo.Mypage;

@Service
public class MyPageService {
    final private MemberServerClient client;

    public MyPageService(MemberServerClient client) {
        this.client = client;
    }

    //1. 마이페이지 요소 가져오기!
    public Mypage getMyPage(long memberId) {

        return null;
    }
}
