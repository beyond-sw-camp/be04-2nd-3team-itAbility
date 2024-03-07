package com.team3.itability.recruitment.repository;

import com.team3.itability.recruitment.vo.RecruitVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RecruitMapper {
    List<RecruitVO> findRecruitList();
}
