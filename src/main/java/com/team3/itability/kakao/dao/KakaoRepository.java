package com.team3.itability.kakao.dao;

import com.team3.itability.kakao.domain.Kakaouser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KakaoRepository extends JpaRepository<Kakaouser, String> {

}
