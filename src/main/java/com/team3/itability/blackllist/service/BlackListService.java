package com.team3.itability.blackllist.service;

import com.team3.itability.blackllist.dao.BlacklistDAO;
import com.team3.itability.blackllist.dto.BlacklistDTO;
import com.team3.itability.member.dao.MemberInfoRepo;
import com.team3.itability.member.dto.MemberInfoDTO;
import com.team3.itability.member.service.MemberInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BlackListService {

    private final MemberInfoRepo memberInfoRepo;
    private final BlacklistDAO blacklistDAO;
    private MemberInfoService memberInfoService;

    @Autowired
    public BlackListService(MemberInfoRepo memberInfoRepo, BlacklistDAO blacklistDAO) {
        this.memberInfoRepo = memberInfoRepo;
        this.blacklistDAO = blacklistDAO;
    }

    @Scheduled(fixedRate = 3600000) // 1시간마다 실행
    @Transactional
    public void checkAndBlacklistMembers() {
        List<MemberInfoDTO> membersToBlacklist = memberInfoRepo.findByMbReportCountGreaterThanEqual(5);

        for (MemberInfoDTO member : membersToBlacklist) {
            BlacklistDTO blacklistDTO = new BlacklistDTO();
            blacklistDTO.setMemberId(member.getMemberId());
            blacklistDAO.save(blacklistDTO);

            member.setBlacklistStatus(true);
            member.incrementBlacklistCount();
            memberInfoRepo.save(member);
        }
    }
}
