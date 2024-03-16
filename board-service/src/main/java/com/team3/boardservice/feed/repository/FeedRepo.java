package com.team3.boardservice.feed.repository;

import com.team3.boardservice.feed.dto.FeedDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedRepo extends JpaRepository<FeedDTO,Integer> {

}
