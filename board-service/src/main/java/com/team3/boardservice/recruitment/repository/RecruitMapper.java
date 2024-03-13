package com.team3.boardservice.recruitment.repository;

import com.team3.boardservice.recruitment.vo.RecruitVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RecruitMapper {
    List<RecruitVO> findRecruitList();
}
