package com.green.firstproject.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.green.firstproject.common.ResponseResult;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.beans.ConstructorProperties;

@Getter
@Setter
public class UserSignInRes extends ResponseResult {


    public UserSignInRes(int code, String message) {
        super(code, message);
    }
}