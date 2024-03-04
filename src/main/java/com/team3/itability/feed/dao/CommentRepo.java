package com.team3.itability.feed.dao;

import com.team3.itability.feed.dto.CommentDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<CommentDTO,Integer> {
}
