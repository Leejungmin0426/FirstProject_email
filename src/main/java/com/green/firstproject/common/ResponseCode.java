package com.green.firstproject.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ResponseCode {
    // 공통 에러
    DATABASE_ERROR("DE"),
    NO_FORBIDDEN("NF"),

    // 회원가입
    OK("OK"),
    DUPLICATE_EMAIL("DE"),
    DUPLICATE_NICKNAME("DN"),
    PASSWORD_FORMAT_ERROR("PFE"),
    PASSWORD_CHECK_ERROR("PCE"),

    // 로그인
    INCORRECT_EMAIL("IE"),
    INCORRECT_PASSWORD("IP"),

    // 유저 찾기
    NO_EXIST_USER("NEU"),

    //필수 사항
    NOT_NULL("NN");

    private final String code;
}