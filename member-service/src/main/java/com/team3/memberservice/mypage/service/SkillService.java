package com.team3.memberservice.mypage.service;

import com.team3.memberservice.mypage.dao.SkillDAO;
import com.team3.memberservice.mypage.dto.ResponseSkill;
import com.team3.memberservice.mypage.entity.SkillEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SkillService {
    private final SkillDAO skillDAO;
    private final ModelMapper modelMapper;

    @Autowired
    public SkillService(SkillDAO skillDAO, ModelMapper modelMapper) {
        this.skillDAO = skillDAO;
        this.modelMapper = modelMapper;
    }

    public ResponseSkill getSkill(int skillId) {
        SkillEntity skill = skillDAO.findById(skillId).orElseThrow();
        return modelMapper.map(skill,ResponseSkill.class);
    }

    public List<ResponseSkill> getAllSkill() {
        List<SkillEntity> skills = skillDAO.findAll();
        List<ResponseSkill> responseSkills=new ArrayList<>();
        skills.forEach(skill -> responseSkills.add(modelMapper.map(skill, ResponseSkill.class)));
        return responseSkills;
    }
}
