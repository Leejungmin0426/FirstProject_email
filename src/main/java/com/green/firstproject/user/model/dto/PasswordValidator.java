package com.green.firstproject.user.model.dto;


public class PasswordValidator {

    public static boolean isValidPassword(String password) {
        if (password == null) {
            return false;
        }
        String passwordRegex = "^(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,15}$";
        return password.matches(passwordRegex);
    }
}