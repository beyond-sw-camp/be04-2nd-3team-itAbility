package com.team3.memberservice.skill.service;

import com.team3.memberservice.skill.dao.SkillDAO;
import com.team3.memberservice.skill.dto.ResponseSkill;
import com.team3.memberservice.skill.entity.MemberSkillEntity;
import com.team3.memberservice.skill.entity.SkillEntity;
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
//    public List<ResponseSkill> getMemberSkill()

    public List<ResponseSkill> getAllSkill() {
        List<SkillEntity> skills = skillDAO.findAll();
        List<ResponseSkill> responseSkills=new ArrayList<>();
        skills.forEach(skill -> responseSkills.add(modelMapper.map(skill, ResponseSkill.class)));
        return responseSkills;
    }


    public List<ResponseSkill> findSkillList(List<MemberSkillEntity> skills){
        List<ResponseSkill> returnValue = new ArrayList<>();
        skills.forEach(skill->{
            SkillEntity skillEntity = skillDAO.findById(skill.getId().getSkillId()).orElseThrow();
            returnValue.add(modelMapper.map(skillEntity,ResponseSkill.class));
        });
        return  returnValue;
    }
}
