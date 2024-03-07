package com.team3.itability.reple.repository;

import com.team3.itability.reple.aggregate.CommentEntity;
import com.team3.itability.reple.dto.CommentDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepo extends JpaRepository<CommentDTO,Integer> {
    List<CommentEntity> findByBoardId(int boardId);
}
