package com.green.firstproject.home;

import com.green.firstproject.common.ResponseCode;
import com.green.firstproject.common.ResponseResult;
import com.green.firstproject.home.model.HomeGetReq;
import com.green.firstproject.home.model.HomeGetRes;
import com.green.firstproject.project.dto.Project;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class HomeService {

    private final HomeMapper mapper;

    @Transactional(readOnly = true)
    public ResponseResult getHome(long leaderNo, String date) {
        log.info("Fetching projects for signedUserNo={} on date={}", leaderNo, date);

        // DB에서 프로젝트 리스트 조회
        List<Project> projects = mapper.getHomeProjects(leaderNo, date);

        // 프로젝트 리스트가 없을 경우 빈 리스트 반환
        if (projects == null || projects.isEmpty()) {
            log.warn("No projects found for signedUserNo={} on date={}", leaderNo, date);
            return new HomeGetRes(ResponseCode.OK.getCode(), Collections.emptyList()); // 빈 리스트 반환
        }

        // 프로젝트 리스트를 포함하여 성공 응답 반환
        return new HomeGetRes(ResponseCode.OK.getCode(), projects);
    }
}