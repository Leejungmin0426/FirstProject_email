package com.green.firstproject.user;

import com.green.firstproject.common.ResponseResult;
import com.green.firstproject.user.model.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
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
    @GetMapping("/{signedUserNo}")
    @Operation(summary = "사용자 정보 조회", description = "RequestParam을 이용해 사용자 정보를 조회")
    public ResponseResult selUserInfo(
            @Parameter(description = "로그인한 유저 PK", example = "3") @PathVariable long signedUserNo,
            @Parameter(description = "프로필 유저 PK", example = "3") @RequestParam("profileUserNo") long profileUserNo) {

        // 요청 객체 생성
        UserInfoGetReq p = new UserInfoGetReq(signedUserNo, profileUserNo);

        // 서비스 호출 후 결과 반환
        return service.selUserInfo(p);
    }


    // 3. 프로필 수정 페이지 (GET)
    @GetMapping("/edit/{userNo}")
    public ResponseResult getUdpProfilePage(@ParameterObject @ModelAttribute UserUpdProfilePageReq p) {
        // 서비스에서 UserUpdProfilePageReq 요청 객체를 사용
        UserUpdProfilePageReq req = new UserUpdProfilePageReq(p.getSignedUserNo(), p.getProfileUserNo());
        return service.getUpdProfilePage(req);
    }






    // 4. 프로필 수정 (PUT)
    @PutMapping("/edit/{userNo}")
    public ResponseResult updateUserProfile(@PathVariable long userNo,
                                            @ModelAttribute UserUpdProfileReq p) {
        // UserUpdProfileReq에 userNo를 설정하여 서비스 호출
        p.setUserNo(userNo);
        return service.updUserProfile(p);
    }
}

