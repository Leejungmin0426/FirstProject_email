package com.green.firstproject.email;


import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmailMapper {
    void sendEmail(String to, String subject, String content, String provider);
    void insertAuthCode(String email, String authCode);
}