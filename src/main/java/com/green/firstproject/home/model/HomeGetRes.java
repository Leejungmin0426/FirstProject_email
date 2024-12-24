package com.green.firstproject.home.model;

import com.green.firstproject.common.ResponseCode;
import com.green.firstproject.common.ResponseResult;
import com.green.firstproject.project.dto.Project;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class HomeGetRes extends ResponseResult {
    private List<Project> projectList;

    public HomeGetRes(String code, List<Project> projectList) {
        super(code); // 부모 클래스의 응답 코드 설정
        this.projectList = projectList;
    }
}