package com.team3.boardservice.reple.repository;

import com.team3.boardservice.feed.dto.FeedDTO;
import com.team3.boardservice.reple.aggregate.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface CommentRepo extends JpaRepository<CommentEntity,Integer> {
    List<CommentEntity> findByBoardId(FeedDTO boardId);


    void deleteAllByBoardIdBoardId(int boardId);
}
