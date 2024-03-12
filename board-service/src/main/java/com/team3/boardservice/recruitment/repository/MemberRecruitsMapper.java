package com.team3.itability.recruitment.repository;

import com.team3.itability.recruitment.vo.MemberRecruitsInfoVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberRecruitsMapper {
    List<MemberRecruitsInfoVO> selectMembersByRecruitId(String recruitId);
}
