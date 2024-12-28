package com.green.firstproject.email.model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {
    // 토큰으로 검색
    VerificationToken findByToken(String token);
}