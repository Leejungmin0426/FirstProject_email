package com.green.firstproject.user.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;



@Getter
@Setter
@Schema(title = "유저 정보 GET 요청")
@ToString
public class UserInfoGetReq {

    @Schema(name = "target_user_no", description = "로그인한 유저 PK", example = "2", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "target_user_no is required")
    private long targetUserNo;

    public UserInfoGetReq(long targetUserNo) {
        this.targetUserNo =targetUserNo;
    }

}