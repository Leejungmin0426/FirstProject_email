package com.green.firstproject.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserUpdProfilePageReq {

    @Schema(title = "응답 코드", description = "상태 코드", example = "200")
    private int code;

    @Schema(title = "응답 메시지", description = "결과 메시지", example = "OK")
    private String message;

    @Schema(title = "변경된 프로필 사진 파일명", description = "변경 후 저장된 프로필 사진 파일명", nullable = true)
    private String savedPicName;


}