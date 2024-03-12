package com.team3.memberservice.snsapi.google.repository;

import com.team3.memberservice.snsapi.google.aggregate.GoogleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoogleRepository extends JpaRepository<GoogleEntity, Long> {
}
