package com.team3.memberservice.skill.controller;

import com.team3.memberservice.skill.dto.ResponseSkill;
import com.team3.memberservice.skill.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/skill")
public class SkillController {
    private final SkillService skillService;
    @Autowired
    public SkillController(SkillService skillService) {
        this.skillService = skillService;
    }

    @GetMapping("/{skillId}")
    public ResponseSkill getSkill(@PathVariable int skillId){
        ResponseSkill responseSkill= skillService.getSkill(skillId);
        System.out.println("responseSkill = " + responseSkill);
        return responseSkill;
    }

    @GetMapping("/skills")
    public List<ResponseSkill> getAllSkill(){
        List<ResponseSkill> responseSkills = skillService.getAllSkill();
        return responseSkills;
    }
}
