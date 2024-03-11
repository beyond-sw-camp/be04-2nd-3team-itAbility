package com.team3.itability.blackllist.repository;

import com.team3.itability.blackllist.aggregate.BlacklistEntity;
import com.team3.itability.blackllist.dto.BlacklistDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface BlacklistRepository extends JpaRepository<BlacklistEntity, Long> {
}
