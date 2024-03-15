package com.team3.boardservice.feed.repository;


import com.team3.boardservice.feed.dto.ImgDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImgRepo extends JpaRepository<ImgDTO,String> {
}
