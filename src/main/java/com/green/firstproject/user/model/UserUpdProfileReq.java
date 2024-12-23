package com.green.firstproject.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class UserUpdProfileReq {

    @Schema(title = "사용자 닉네임", description = "변경할 닉네임", example = "미니미닝")
    private String nickname;

    @Schema(title = "비밀번호", description = "변경할 비밀번호", example = "mini0426!")
    private String password;

    @Schema(title = "비밀번호 확인", description = "비밀번호 확인 필드", example = "mini0426!")
    private String passwordConfim;

    @Schema(title = "프로필 사진", description = "프로필 사진 파일 또는 null 값 가능")
    private MultipartFile profilePic;

    @Schema(title = "사용자 본인 상태 메시지", description = "사용자의 상태 메시지 (null 허용)", example = "Just do it!")
    private String userStatusMessage;

    @JsonIgnore
    private String profilePicName;

    @JsonIgnore
    private long signedUserNo;
}