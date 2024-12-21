package com.green.firstproject.user.model;
import com.green.firstproject.common.ResponseResult;
import com.green.firstproject.user.model.dto.UserInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInfoGetRes extends ResponseResult {

    @Schema(title = "사용자 정보")
    private UserInfo userInfo;

    public UserInfoGetRes(String code, UserInfo userInfo) {
        super(code); // ResponseResult의 생성자 호출
        this.userInfo = userInfo;
    }
}