package com.green.firstproject.project;

import com.green.firstproject.common.ResponseResult;
import com.green.firstproject.project.dto.Project;
import com.green.firstproject.project.model.ProjectUpdateReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("project")
public class ProjectController {

    private final ProjectService service;

    @PutMapping("/edit/{projectNo}")
    public ResponseResult updateProject(
            @PathVariable long projectNo,
            @RequestParam long signedUserNo,
            @RequestBody ProjectUpdateReq p) {
        // 요청 객체에 프로젝트 번호와 사용자 번호 설정
        p.setProjectNo(projectNo);
        p.setSignedUserNo(signedUserNo);

        // 서비스 호출 및 결과 반환
        return service.updateProject(p);
    }
}
