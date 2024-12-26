package com.green.firstproject.user;

import com.green.firstproject.user.model.UserInfoGetReq;
import com.green.firstproject.user.model.dto.PasswordValidator;
import com.green.firstproject.user.model.dto.UserInfo;
import org.mindrot.jbcrypt.BCrypt;

public class test {
    public static void main(String[] args) {
//        String rawPassword = "mini0426!"; // 입력된 평문 비밀번호
//        String hashedPassword = "$2a$10$gVgU/CkA8TrFmZT1T7hvN.OU5jTQgtN5GV.lcTmgXrkUtJcIe9B2y"; // 새로 업데이트된 해시
//
//        if (BCrypt.checkpw(rawPassword, hashedPassword)) {
//            System.out.println("비밀번호가 일치합니다!");
//        } else {
//            System.out.println("비밀번호가 일치하지 않습니다!");
//        }
//
//    }

            System.out.println(PasswordValidator.isValidPassword("Minmin0426!")); // true
            System.out.println(PasswordValidator.isValidPassword("minmin")); // false
        }

}
