package com.team3.boardservice.recruitment.repository;

import com.team3.boardservice.recruitment.aggregate.MemberRecruitsInfoDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRecruitsInfoRepo extends JpaRepository<MemberRecruitsInfoDTO, Integer> {

    List<MemberRecruitsInfoDTO> findAllByMemberInfoDTO(long memberId);
    List<MemberRecruitsInfoDTO> deleteAllByMemberInfoDTO(long memberId);
}
