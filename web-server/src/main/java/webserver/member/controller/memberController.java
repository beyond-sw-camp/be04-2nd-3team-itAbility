package webserver.member.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import webserver.member.service.MyPageService;
import webserver.member.vo.Mypage;

@Controller
@RequestMapping("/mypage")
public class memberController {
    private final MyPageService myPageService;

    public memberController(MyPageService myPageService) {
        this.myPageService = myPageService;
    }


    @GetMapping("/{memberId}")
    public String mypage(@PathVariable long memberId, ModelAndView mv){
//        Mypage mypage = myPageService.getMyPage(memberId);
//        mv.addObject(mypage);
        return "main";
    }


}
