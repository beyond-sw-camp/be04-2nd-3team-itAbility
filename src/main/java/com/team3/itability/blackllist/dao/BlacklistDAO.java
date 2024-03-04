package com.team3.itability.blackllist.dao;

import com.team3.itability.blackllist.dto.BlacklistDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlacklistDAO extends JpaRepository<BlacklistDTO, Long> {
}
