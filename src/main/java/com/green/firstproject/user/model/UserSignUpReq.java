package com.green.firstproject.user.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Schema(description = "회원가입 요청")
public class UserSignUpReq {

    @Schema(description = "이메일 주소", example = "minmin0426@naver.com", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "이메일 주소는 필수입니다.")
    private String email;

    @Schema(description = "유저 ID", example = "쩡미니미니", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "유저 ID는 필수입니다.")
    @Size(min = 5, max = 20, message = "유저 ID는 5자 이상 20자 이하로 입력해야 합니다.")
    private String userId;

    @Schema(description = "비밀번호", example = "Minmin0426!", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "비밀번호는 필수입니다.")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,15}$",

            message = "비밀번호는 8자 이상 16자 미만이며, 영문 소문자, 숫자, 특수문자를 최소 1개 포함해야 합니다."
    )
    private String password;

    @Schema(description = "비밀번호 확인", example = "Minmin0426!", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "비밀번호 확인은 필수입니다.")
    private String passwordConfirm;

    @Schema(description = "닉네임",  example = "미니공쥬", requiredMode = Schema.RequiredMode.REQUIRED)
    private String nickname;


    @Schema(description = "활성화 여부", example = "0", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private int enabled = 0; // 이메일 인증 완료 전 기본값

    @Schema(description = "첫 로그인 여부", example = "1", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private int firstLogin = 1; // 회원가입 시 기본값

    /**
     * 비밀번호와 비밀번호 확인이 일치하는지 검증하는 메서드
     *
     * @return true if password and passwordConfirm match, false otherwise
     */
    public boolean isPasswordConfirmed() {
        return this.password != null && this.password.equals(this.passwordConfirm);
    }
    }