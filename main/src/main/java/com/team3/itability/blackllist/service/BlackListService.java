package com.team3.itability.blackllist.service;

import com.team3.itability.blackllist.aggregate.BlacklistEntity;
import com.team3.itability.blackllist.repository.BlacklistRepository;
import com.team3.itability.blackllist.dto.BlacklistDTO;
import com.team3.itability.member.dao.MemberInfoRepo;
import com.team3.itability.member.dto.MemberInfoDTO;
import com.team3.itability.member.service.MemberInfoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class BlackListService {

    private final MemberInfoRepo memberInfoRepo;
    private final BlacklistRepository blacklistDAO;
    private final ModelMapper modelMapper;
    private MemberInfoService memberInfoService;

    @Autowired
    public BlackListService(MemberInfoRepo memberInfoRepo, BlacklistRepository blacklistDAO, ModelMapper modelMapper) {
        this.memberInfoRepo = memberInfoRepo;
        this.blacklistDAO = blacklistDAO;
        this.modelMapper = modelMapper;
    }

    @Scheduled(fixedRate = 3600000) // 1시간마다 실행
    @Transactional
    public void checkAndBlacklistMembers() {
        List<MemberInfoDTO> membersToBlacklist = memberInfoRepo.findByMbReportCountGreaterThanEqual(5);

        for (MemberInfoDTO member : membersToBlacklist) {
            BlacklistDTO blacklistDTO = new BlacklistDTO();
            blacklistDTO.setMemberId(member.getMemberId());
            blacklistDTO.setBlacklist_date( new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
            BlacklistEntity blacklistEntity = modelMapper.map(blacklistDTO, BlacklistEntity.class);
            blacklistDAO.save(blacklistEntity);


            member.setBlacklistStatus(true);
            member.incrementBlacklistCount();
            member.decrementReportCount(5);
            memberInfoRepo.save(member);
        }
    }
}
