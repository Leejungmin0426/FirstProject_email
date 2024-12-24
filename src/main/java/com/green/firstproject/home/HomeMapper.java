package com.green.firstproject.home;

import com.green.firstproject.project.dto.Project;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface HomeMapper {


    List<Project> getHomeProjects( long signedUserNo, String date);
}