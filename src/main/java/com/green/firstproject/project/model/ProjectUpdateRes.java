package com.green.firstproject.project.model;

import com.green.firstproject.common.ResponseCode;
import com.green.firstproject.common.ResponseResult;
import com.green.firstproject.project.dto.MemberInfo;
import com.green.firstproject.project.dto.Project;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProjectUpdateRes extends ResponseResult {
    private String title;

    private String description;
    private String startAt;
    private String deadLine;
    private List<MemberInfo> memberList;

    public ProjectUpdateRes(ResponseCode code, Project project) {
        super(code.getCode());
        this.title = project.getTitle();
        this.description = project.getDescription();
        this.startAt = project.getStartAt();
        this.deadLine = project.getDeadLine();
        // memberList는 별도 로직으로 설정 필요
    }
}