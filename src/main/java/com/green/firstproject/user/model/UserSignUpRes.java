package com.green.firstproject.user.model;

import com.green.firstproject.common.exception.ResponseResult;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserSignUpRes extends ResponseResult {

    public UserSignUpRes(String code) {
        super(code); // ResponseResult의 생성자 호출
    }
}
