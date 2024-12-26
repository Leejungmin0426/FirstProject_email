package com.green.firstproject.user;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordResetExample {
    public static void main(String[] args) {
        // 사용자가 새로 설정한 비밀번호
        String newPassword = "love0426!";

        // 비밀번호 해싱
        String hashedPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt());

        // 데이터베이스에 저장 (여기서는 예제로 출력)
        System.out.println("새 비밀번호 해시: " + hashedPassword);
    }
}