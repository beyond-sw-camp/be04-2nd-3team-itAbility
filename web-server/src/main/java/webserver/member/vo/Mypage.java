package webserver.member.vo;

import java.util.List;

public class Mypage {
    private long memberId;
    private String name;
    private String nickname;
    private String birthday;

    private int follower;
    private int follwing;

    private List<String> skill;
    private List<String> recruitCategory;
    private List<Career> career;
}
