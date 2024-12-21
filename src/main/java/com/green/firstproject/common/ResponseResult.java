package com.green.firstproject.common;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@AllArgsConstructor
public class ResponseResult {
    @Schema(title = "응답 코드")
    private String code;

    @ResponseStatus(HttpStatus.OK)
    public static ResponseResult success() {
        return new ResponseResult(ResponseCode.OK.getCode());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public static ResponseResult duplicateEmail() {
        return new ResponseResult(ResponseCode.DUPLICATE_EMAIL.getCode());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public static ResponseResult duplicateNickname() {
        return new ResponseResult(ResponseCode.DUPLICATE_NICKNAME.getCode());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public static ResponseResult passwordFormatError() {
        return new ResponseResult(ResponseCode.PASSWORD_FORMAT_ERROR.getCode());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public static ResponseResult passwordCheckError() {
        return new ResponseResult(ResponseCode.PASSWORD_CHECK_ERROR.getCode());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public static ResponseResult incorrectEmail() {
        return new ResponseResult(ResponseCode.INCORRECT_EMAIL.getCode());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public static ResponseResult incorrectPassword() {
        return new ResponseResult(ResponseCode.INCORRECT_PASSWORD.getCode());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public static ResponseResult noExistUser() {
        return new ResponseResult(ResponseCode.NO_EXIST_USER.getCode());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public static ResponseResult serverError() {
        return new ResponseResult(ResponseCode.DATABASE_ERROR.getCode());
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    public static ResponseResult noPermission() {
        return new ResponseResult(ResponseCode.NO_FORBIDDEN.getCode());
    }


}