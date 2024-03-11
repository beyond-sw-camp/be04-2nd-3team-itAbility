package com.team3.itability.snsapi.google.repository;

import com.team3.itability.snsapi.google.aggregate.GoogleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoogleRepository extends JpaRepository<GoogleEntity, Long> {
}
