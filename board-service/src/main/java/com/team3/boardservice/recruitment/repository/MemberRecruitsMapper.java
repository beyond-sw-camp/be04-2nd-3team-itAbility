package com.team3.boardservice.recruitment.repository;

import com.team3.boardservice.recruitment.vo.MemberRecruitsInfoVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberRecruitsMapper {
    List<MemberRecruitsInfoVO> selectMembersByRecruitId(String recruitId);
}
