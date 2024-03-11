package com.team3.itability.follow2user.service;

import com.team3.itability.follow2user.aggregate.Follow;
import com.team3.itability.follow2user.dto.FollowDTO;
import com.team3.itability.follow2user.repository.FollowRepository;
import com.team3.itability.member.dao.MemberInfoRepo;
import com.team3.itability.member.dto.MemberInfoDTO;
import com.team3.itability.member.service.MemberInfoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FollowService {


    private final ModelMapper modelMapper;

    private final FollowRepository followRepository;

    private final MemberInfoService memberInfoService;
    private final MemberInfoRepo memberInfoRepo;

    @Autowired
    public FollowService(ModelMapper modelMapper, FollowRepository followRepository
            , MemberInfoService memberInfoService, MemberInfoRepo memberInfoRepo) {
        this.modelMapper = modelMapper;
        this.followRepository = followRepository;
        this.memberInfoService = memberInfoService;
        this.memberInfoRepo = memberInfoRepo;
    }

    public FollowDTO findFollowById(int followId){
        Follow follow = followRepository.findById(followId).orElseThrow(IllegalArgumentException::new);

        return modelMapper.map(follow, FollowDTO.class);
    }


    public List<FollowDTO> findFollowList() {
        List<Follow> followList = followRepository.findAll(Sort.by("followId"));

        return followList.stream().map(follow -> modelMapper.map(follow, FollowDTO.class)).collect(Collectors.toList());
    }

    public Follow addFollow(Long followingId, Long followedId) {
        MemberInfoDTO following = memberInfoService.findMemberInfoById(followingId);
        MemberInfoDTO followed = memberInfoService.findMemberInfoById(followedId);

        if (following == null || followed == null) {
            // 에러 처리
            return null;
        }

        Follow follow = new Follow();
        follow.setFollowing(following);
        follow.setFollowed(followed);

        return followRepository.save(follow);
    }

    public List<Follow> getFollowedByFollowingId(Long followingId) {
        MemberInfoDTO following = memberInfoRepo.findById(followingId).orElse(null);
        if (following == null) {
            return Collections.emptyList();
        }
        return followRepository.findByFollowing(following);
    }

    @Transactional
    public void deleteFollow(int followId) {
        followRepository.deleteById(followId);
    }

}