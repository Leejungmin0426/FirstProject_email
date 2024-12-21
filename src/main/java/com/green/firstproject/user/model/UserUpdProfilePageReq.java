package com.green.firstproject.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.beans.ConstructorProperties;

@Getter
@Setter
public class UserUpdProfilePageReq {
    @Schema(name = "profile_user_no", description = "프로필 유저 PK", type = "number", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private long profileUserNo;

    @Schema(name = "signed_user_no", description = "로그인한 유저 PK", type = "number", example = "2", requiredMode = Schema.RequiredMode.REQUIRED)
    private long signedUserNo;

    @ConstructorProperties({"signed_user_no", "profile_user_no"})
    public UserUpdProfilePageReq(long signedUserNo, long profileUserNo){
        this.signedUserNo = signedUserNo;
        this.profileUserNo = profileUserNo;
    }
}
