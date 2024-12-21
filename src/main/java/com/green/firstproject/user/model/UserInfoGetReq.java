package com.green.firstproject.user.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.ToString;

import java.beans.ConstructorProperties;


@Getter
@Schema(title = "유저 정보 GET요청")
@ToString
public class UserInfoGetReq {

    @Schema(name = "signed_user_no", description = "로그인한 유저 PK", type = "number", example = "2", requiredMode = Schema.RequiredMode.REQUIRED)
    private long signedUserNo;

    @Schema(name = "profile_user_no", description = "프로필 유저 PK", type = "number", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private long profileUserNo;


    @ConstructorProperties({"signed_user_no", "profile_user_no"})
    public UserInfoGetReq(long signedUserNo, long profileUserNo){
        this.signedUserNo = signedUserNo;
        this.profileUserNo = profileUserNo;
    }
}
