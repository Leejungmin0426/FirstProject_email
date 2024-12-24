package com.green.firstproject.user.model;
import com.green.firstproject.common.ResponseCode;
import com.green.firstproject.common.ResponseResult;
import com.green.firstproject.user.model.dto.UserInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInfoGetRes extends ResponseResult {

    @Schema(title = "이메일")
    private String email;

    @Schema(title = "닉네임")
    private String nickname;

    @Schema(title = "상태 메시지")
    private String userStatusMessage;

    @Schema(title = "프로필 사진 URL")
    private String profilePic;

    @Schema(title = "본인 여부", description = "요청한 사용자가 자신의 정보를 요청했는지 여부")
    private boolean isMyInfo; // 본인 여부 필드 추가

    // 기본 생성자
    public UserInfoGetRes() {
        super(ResponseCode.OK.getCode()); // ResponseResult 기본 생성자 호출
    }

    // 필요한 경우 매개변수 생성자도 추가
    public UserInfoGetRes(String code, String email, String nickname, String userStatusMessage, String profilePic) {
        super(code); // ResponseResult 생성자 호출
        this.email = email;
        this.nickname = nickname;
        this.userStatusMessage = userStatusMessage;
        this.profilePic = profilePic;
    }
}