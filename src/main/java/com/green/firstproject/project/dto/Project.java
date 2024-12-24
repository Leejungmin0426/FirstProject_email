package com.green.firstproject.project.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Project {
    private long projectNo;           // 프로젝트 번호
    private long leaderNo;            // 리더(작성자) 번호
    private String title;             // 프로젝트 제목
    private String description;       // 프로젝트 설명
    private String startAt;           // 프로젝트 시작 날짜
    private String deadLine;          // 프로젝트 마감 날짜
}