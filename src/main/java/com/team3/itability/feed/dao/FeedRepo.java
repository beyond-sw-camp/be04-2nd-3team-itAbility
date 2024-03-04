package com.team3.itability.feed.dao;

import com.team3.itability.feed.dto.FeedDTO;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedRepo extends JpaRepository<FeedDTO,Integer> {
}
