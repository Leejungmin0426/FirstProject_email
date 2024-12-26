package com.green.firstproject.project;

import com.green.firstproject.common.exception.ResponseCode;
import com.green.firstproject.common.exception.ResponseResult;
import com.green.firstproject.project.dto.MemberInfo;
import com.green.firstproject.project.dto.Project;
import com.green.firstproject.project.model.ProjectSearchUserRes;
import com.green.firstproject.project.model.ProjectUpdateReq;
import com.green.firstproject.project.model.ProjectUpdateRes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class ProjectService {

    private final ProjectMapper mapper;

    @Transactional
    public ResponseResult updateProject(ProjectUpdateReq p) {

        // 1. 요청 데이터 검증
        if (p == null || p.getProjectNo() <= 0 || p.getSignedUserNo() <= 0) {
            return ResponseResult.badRequest(ResponseCode.NOT_NULL); // 필수 값 누락 시 "NN" 반환
        }

        // 2. 프로젝트 조회
        Project project = mapper.getProjectById(p.getProjectNo());
        if (project == null) {
            return ResponseResult.badRequest(ResponseCode.NO_EXIST_PROJECT); // 프로젝트가 없는 경우
        }


        // 3. 권한 검증
        if (project.getLeaderNo() != p.getSignedUserNo()) {
            return ResponseResult.noPermission(); // 권한이 없는 경우
        }

        // 4. 프로젝트 수정
        int updatedRows = mapper.updateProject(p);
        if (updatedRows <= 0) {
            return ResponseResult.databaseError(); // 수정 실패
        }

        // 5. 수정된 프로젝트와 멤버 정보 조회
        Project updatedProject = mapper.getProjectById(p.getProjectNo());
        List<MemberInfo> members = mapper.getProjectMembers(p.getProjectNo());

        ProjectUpdateRes projectUpdateRes = new ProjectUpdateRes(ResponseCode.OK, updatedProject);
        projectUpdateRes.setMemberList(members); // 멤버 리스트 설정

        // 6. 성공 응답 반환
        return projectUpdateRes;
    }


    @Transactional
    public ResponseResult searchUserByNickname(String nickname) {
        // 닉네임으로 사용자 검색
        ProjectSearchUserRes user = mapper.searchUserByNickname(nickname);

        if (user == null) {
            // 사용자 없음
            return ResponseResult.badRequest(ResponseCode.NO_EXIST_USER);
        }

        // 사용자 정보 반환
        return user; // ProjectSearchUserRes 반환
    }
}



