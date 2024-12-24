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

import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class HomeService {

    private final HomeMapper mapper;

    @Transactional(readOnly = true)
    public ResponseResult getHome(long signedUserNo) {
        log.info("Fetching projects for signedUserNo={}", signedUserNo);

        // DB에서 프로젝트 리스트 조회
        List<Project> projects = mapper.getHomeProjects(signedUserNo);

        if (projects == null || projects.isEmpty()) {
            log.warn("No projects found for signedUserNo={}", signedUserNo);
            return ResponseResult.success(); // 빈 응답
        }

        return new HomeGetRes(ResponseCode.OK.getCode(), projects);
    }
}