package com.team3.reportservice.blackllist.service;

import com.team3.reportservice.blackllist.aggregate.BlacklistEntity;
import com.team3.reportservice.blackllist.dto.BlacklistDTO;
import com.team3.reportservice.blackllist.repository.BlacklistRepository;
import com.team3.reportservice.client.MemberClient;
import com.team3.reportservice.report.aggregate.Member;
import com.team3.reportservice.report.repository.ResponseMemberRepository;
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

    private final ResponseMemberRepository memberInfoRepo;
    private final BlacklistRepository blacklistDAO;
    private final ModelMapper modelMapper;
    private final MemberClient memberClient;

    @Autowired
    public BlackListService(ResponseMemberRepository memberInfoRepo, BlacklistRepository blacklistDAO, ModelMapper modelMapper, MemberClient memberClient) {
        this.memberInfoRepo = memberInfoRepo;
        this.blacklistDAO = blacklistDAO;
        this.modelMapper = modelMapper;
        this.memberClient = memberClient;
    }

    @Scheduled(fixedRate = 3600000) // 1시간마다 실행
    @Transactional
    public void checkAndBlacklistMembers() {
        List<Member> membersToBlacklist = memberInfoRepo.findByMbReportCountGreaterThanEqual(5);

        for (Member member : membersToBlacklist) {
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
