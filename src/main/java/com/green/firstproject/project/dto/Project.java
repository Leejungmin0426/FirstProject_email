package com.green.firstproject.project.dto;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
public class Project {
    private Long projectNo;           // 프로젝트 번호
    private Long leaderNo;            // 리더(작성자) 번호
    private String title;             // 프로젝트 제목
    private String description;       // 프로젝트 설명
    private String startAt;           // 프로젝트 시작 날짜
    private String deadLine;          // 프로젝트 마감 날짜
    private LocalDateTime createdAt;  // 프로젝트 생성 일시
    private LocalDateTime updatedAt;  // 프로젝트 수정 일시
}