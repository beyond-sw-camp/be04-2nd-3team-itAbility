package com.team3.boardservice.feed.repository;


import com.team3.boardservice.feed.dto.LikeDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikeRepo extends JpaRepository<LikeDTO,Integer> {
    List<LikeDTO> findByBoardIdBoardId(int boardId);

    void deleteAllByBoardIdBoardId(int boardId);
}
