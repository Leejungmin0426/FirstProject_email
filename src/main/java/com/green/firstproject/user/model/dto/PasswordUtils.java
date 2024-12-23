package com.green.firstproject.user.model.dto;

public class PasswordUtils {
    // 비밀번호 형식 검증
    public static boolean isValidPasswordFormat(String password) {
        if (password == null) return false;
        return password.matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,16}$");
    }
}