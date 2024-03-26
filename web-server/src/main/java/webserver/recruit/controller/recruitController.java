package webserver.recruit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/recruit")
public class recruitController {

    @GetMapping("/{recruitId}")
    public String recruit() {return "recruit/detail";}

    @GetMapping("/list")
    public String recruitList() {return "recruit/list";}

}
