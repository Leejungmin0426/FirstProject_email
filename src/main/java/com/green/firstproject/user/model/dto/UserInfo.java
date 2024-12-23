package com.green.firstproject.user.model.dto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(title = "사용자 정보")
public class UserInfo {

    @Schema(title = "유저 번호")
    private Long signedUserNo;

    @Schema(title = "사용자 본인 이메일", example = "miniming@naver.com", requiredMode = Schema.RequiredMode.REQUIRED)
    private String email;

    @Schema(title = "사용자 본인 닉네임", example = "minimini", requiredMode = Schema.RequiredMode.REQUIRED)
    private String nickname;

    @Schema(title = "사용자 본인 상태 메시지", description = "사용자의 상태 메시지 (null 허용)", example = "Just do it!")
    private String userStatusMessage; // 상태 메시지

    @Schema(title = "프로필 사진", description = "프로필 사진의 URL 또는 null 허용")
    private String profilePic; // 프로필 사진 URL

}
