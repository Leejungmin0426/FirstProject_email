package com.green.firstproject.project;

import com.green.firstproject.common.exception.ResponseResult;
import com.green.firstproject.project.model.ProjectUpdateReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("project")
public class ProjectController {

    private final ProjectService service;


    @PutMapping
    public ResponseResult updateProject(@RequestBody ProjectUpdateReq p) {
        log.info("Updating projectNo={}, by signedUserNo={}", p.getProjectNo(), p.getSignedUserNo());

        // 서비스 호출 및 결과 반환
        return service.updateProject(p);
    }


    @GetMapping("search-user")
    public ResponseResult searchUserByNickname (@RequestParam String nickname){
        return service.searchUserByNickname(nickname);
    }
}
