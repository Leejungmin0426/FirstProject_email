package com.green.firstproject.user.model;

import com.green.firstproject.common.ResponseCode;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;


import com.green.firstproject.common.ResponseResult;
import com.green.firstproject.user.model.dto.UserInfo;


@Getter
@Setter
@Schema(title = "프로필 수정 페이지 응답")
public class UserUpdProfilePageRes extends ResponseResult {

    @Schema(title = "수정 대상 사용자 정보", description = "수정하려는 사용자의 프로필 정보")
    private UserInfo userInfo;

    public UserUpdProfilePageRes(String code, UserInfo userInfo) {
        super(code); // ResponseResult의 생성자 호출
        this.userInfo = userInfo;
    }
}