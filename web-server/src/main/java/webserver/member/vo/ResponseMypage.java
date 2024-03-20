package webserver.member.vo;

import java.util.List;

public class ResponseMypage {
    private long memberId;
    private String name;
    private String nickname;
    private String birthday;

    private int follower;
    private int follwing;

    private List<String> skill;
    private List<String> recruitCategory;
    private List<ResponseCareer> career;
}
