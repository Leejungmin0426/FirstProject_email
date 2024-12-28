package com.green.firstproject.email.model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    // 사용자 정의 메서드 추가 가능
    User findByEmail(String email); // 이메일로 사용자 검색
}