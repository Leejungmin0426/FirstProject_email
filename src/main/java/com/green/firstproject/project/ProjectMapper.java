package com.green.firstproject.project;

import com.green.firstproject.project.dto.MemberInfo;
import com.green.firstproject.project.dto.Project;
import com.green.firstproject.project.model.ProjectUpdateReq;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProjectMapper {

    // 프로젝트 상세 조회
    Project getProjectById(long projectNo);

    // 프로젝트 수정
    int updateProject(ProjectUpdateReq p);

    // 프로젝트 멤버 조회
    List<MemberInfo> getProjectMembers(long projectNo);
}
