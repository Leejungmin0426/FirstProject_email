package com.green.firstproject.project.model;

import com.green.firstproject.common.exception.ResponseCode;
import com.green.firstproject.common.exception.ResponseResult;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Schema(description = "프로젝트 구성원 검색 결과")
public class ProjectSearchUserRes extends ResponseResult {

    @Schema(description = "사용자의 닉네임", example = "TestUser", required = true)
    private String nickname;

    @Schema(description = "사용자 번호", example = "1", required = true)
    private long userNo;

    @Schema(description = "사용자 프로필 사진")
    private String pic;

    @Schema(description = "일정 존재 여부", example = "true")
    private boolean existSchedule;

    // 기본 생성자
    public ProjectSearchUserRes() {
        super(ResponseCode.OK.getCode()); // ResponseResult 기본 생성자 호출
    }

    public ProjectSearchUserRes(String code, String nickname, long userNo, String pic, boolean existSchedule) {
        super(code);
        this.nickname = nickname;
        this.userNo = userNo;
        this.pic = pic;
        this.existSchedule = existSchedule;
    }
}