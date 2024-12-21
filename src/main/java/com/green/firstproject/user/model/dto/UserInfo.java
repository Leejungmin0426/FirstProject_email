package com.green.firstproject.user.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(title = "사용자 정보")
public class UserInfo {

    @Schema(title = "사용자 본인 이메일", example = "miniming@naver.com", requiredMode = Schema.RequiredMode.REQUIRED)
    private String email;

    @Schema(title = "사용자 본인 닉네임", example = "minimini", requiredMode = Schema.RequiredMode.REQUIRED)
    private String nickname;

    @Schema(title = "사용자 본인 상태 메시지", description = "사용자의 상태 메시지 (null 허용)", example = "Just do it!")
    private String userStatusMessage; // 상태 메시지

    @Schema(title = "프로필 사진", description = "프로필 사진의 URL 또는 null 허용", example = "https://example.com/profile.jpg")
    private String profilePic; // 프로필 사진 URL

    @Schema(title = "유저 넘버", description = "유저 고유 넘버", requiredMode = Schema.RequiredMode.REQUIRED, example = "3")
    private long userNo;

    @Schema(title = "첫 로그인 여부", description = "첫 로그인 여부를 나타내는 값", example = "true")
    private boolean firstLogin;

    @JsonIgnore
    private String password; // 비밀번호는 숨김 처리


    // 전체 필드를 포함한 생성자 추가 (MyBatis 매핑 시 유용)
    public UserInfo(String email, String nickname, String userStatusMessage, String profilePic, long userNo, boolean firstLogin) {
        this.email = email;
        this.nickname = nickname;
        this.userStatusMessage = userStatusMessage;
        this.profilePic = profilePic;
        this.userNo = userNo;
        this.firstLogin = firstLogin;
    }
}
