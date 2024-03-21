package webserver.member.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/mypage")
public class memberController {

    //1.마이페이지   -fin
    @GetMapping("/{memberId}")
    public String mypage(){
        return "mypage/mypage-2";
    }
    //2.프로필사진수정페이지
    @GetMapping("/image")
    public String image(){
        return "mypage/modify-image";
    }
    //3.이름,닉네임,생년월일,전화번호 수정페이지
    @GetMapping("/member")
    public String member(){
        return "mypage/modify-member";
    }

    //4.학력수정페이지
    @GetMapping("/degree")
    public String degree(){
        return "mypage/modify-degree";
    }

    //5.스킬수정페이지
    @GetMapping("/skill")
    public String skill(){
        return "mypage/modify-skill";
    }

    //6.전문분야수정페이지
    @GetMapping("/recruit")
    public String recruit(){
        return "mypage/modify-recruit";
    }

    //7.경력수정페이지
    @GetMapping("/career")
    public String career(){
        return "mypage/modify-career";
    }

}
