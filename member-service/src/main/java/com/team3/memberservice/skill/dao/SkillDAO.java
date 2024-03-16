package com.team3.memberservice.skill.dao;


import com.team3.memberservice.skill.entity.SkillEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillDAO extends JpaRepository<SkillEntity,Integer> {

}
