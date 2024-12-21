package com.green.firstproject.user;

import org.mindrot.jbcrypt.BCrypt;

public class test {
    public static void main(String[] args) {
        String rawPassword = "mini"; // 입력된 평문 비밀번호
        String hashedPassword = "sss"; // 새로 업데이트된 해시

        if (BCrypt.checkpw(rawPassword, hashedPassword)) {
            System.out.println("비밀번호가 일치합니다!");
        } else {
            System.out.println("비밀번호가 일치하지 않습니다!");
        }
    }
}
