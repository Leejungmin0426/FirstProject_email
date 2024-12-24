package com.green.firstproject.user.model;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;



@Getter
@Setter
@Schema(title = "유저 정보 GET 요청")
@ToString
public class UserInfoGetReq {

    @Schema(name = "target_user_no", description = "타겟 유저 PK", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)

    private long targetUserNo;

    @Schema(name = "signed_user_no", description = "로그인한 유저 PK", example = "2", requiredMode = Schema.RequiredMode.REQUIRED)
    private long signedUserNo;

    public UserInfoGetReq(long targetUserNo, long signedUserNo) {
        this.targetUserNo = targetUserNo;
        this.signedUserNo = signedUserNo;
    }

}