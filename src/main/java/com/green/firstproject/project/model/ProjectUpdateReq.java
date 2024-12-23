package com.green.firstproject.project.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Schema(title = "프로젝트 수정 요청")
public class ProjectUpdateReq {
    private long projectNo; // 프로젝트 번호
    private long signedUserNo; // 요청 사용자 번호
    private String name; // 프로젝트 이름
    private String description; // 프로젝트 설명
    private String startAt; // 시작 날짜
    private String deadLine; // 마감 날짜
    private List<Long> memberList; // 프로젝트 멤버 리스트
}