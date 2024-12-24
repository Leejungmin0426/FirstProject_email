package com.green.firstproject.user;

import com.green.firstproject.common.ResponseResult;
import com.green.firstproject.user.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("user")
public class UserController {

    private final UserService service;


    // 1. 유저 로그인 (POST)
    @PostMapping("/sign-in")
    public ResponseResult userSignIn(@RequestBody UserSignInReq p) {
        System.out.println("요청된 이메일: " + p.getEmail());
        System.out.println("요청된 비밀번호: " + p.getPassword());
        return service.userSignIn(p);
    }


    // 2. 사용자 정보 조회 (GET)
    @GetMapping("/{targetUserNo}")
    public ResponseResult selUserInfo(
            @PathVariable long targetUserNo,
            @RequestHeader long signedUserNo
    ) {
        UserInfoGetReq req = new UserInfoGetReq(targetUserNo, signedUserNo);
        return service.selUserInfo(req);
    }
}
