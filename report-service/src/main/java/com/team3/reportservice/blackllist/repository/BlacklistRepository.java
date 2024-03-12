package com.team3.reportservice.blackllist.repository;

import com.team3.reportservice.blackllist.aggregate.BlacklistEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface BlacklistRepository extends JpaRepository<BlacklistEntity, Long> {
}
