package com.team3.memberservice.config;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("health_chaeck")
    public String hc() {
        return "fine";
    }
}
