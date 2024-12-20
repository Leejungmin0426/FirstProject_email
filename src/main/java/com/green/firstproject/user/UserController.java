package com.green.firstproject.user;

import com.green.firstproject.common.ResponseResult;
import com.green.firstproject.user.model.*;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("user")
public class UserController {

    private final UserService userService;



    /**
     * 사용자 로그인
     * POST /api/user/sign-in
     */
    @PostMapping("/sign-in")
    public ResponseResult signIn(@RequestBody UserSignInReq req) {
        // 서비스 호출
        return userService.selUserByUid(req);
    }


    /**
     * 유저 상세 페이지 조회
     * GET /api/user/{userNo}
     */
    @GetMapping("/{userNo}")
    public ResponseResult selUserInfo(@ParameterObject @ModelAttribute UserInfoGetReq p) {
        // 응답 처리
        return userService.selUserInfo(p);
    }

    /**
     * 프로필 수정 페이지 조회
     */
    @GetMapping("/edit/{userNo}")
    public ResponseResult getProfilePage(@ParameterObject @ModelAttribute UserUpdProfilePageReq p) {

        // 성공 응답 반환
        return userService.getProfilePage(p);
    }

    /**
     * 프로필 수정
     */
    @PutMapping("/edit/{userNo}")
    public ResponseResult updUserProfile(@RequestBody UserUpdProfileReq p) {

        // 서비스 호출
        return userService.updUserProfile(p);
    }
}