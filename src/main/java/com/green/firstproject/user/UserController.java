package com.green.firstproject.user;

import com.green.firstproject.common.ResponseCode;
import com.green.firstproject.common.ResponseResult;
import com.green.firstproject.user.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    public ResponseResult selUserInfo(@PathVariable long targetUserNo) {
        // 데이터 유효성 검증
        if (targetUserNo <= 0) {
            return ResponseResult.badRequest(ResponseCode.NO_EXIST_USER); // 잘못된 요청 응답
        }

        UserInfoGetReq p = new UserInfoGetReq(targetUserNo);
        return service.selUserInfo(p);
    }


    // 4. 프로필 수정 (PUT)
    @PutMapping("/{signedUserNo}")
    public ResponseResult updateUserProfile(
            @PathVariable(name = "signedUserNo") long signedUserNo,
            @RequestPart(name = "profilePic", required = false) MultipartFile profilePic, // 선택적 프로필 사진
            @RequestParam(name = "password", required = false) String password, // 선택적 필드
            @RequestParam(name = "passwordConfim", required = false) String passwordConfim, // 선택적 필드
            @RequestParam(name = "nickname", required = false) String nickname, // 선택적 필드
            @RequestParam(name = "userStatusMessage", required = false) String userStatusMessage) { // 선택적 필드

        // 요청 객체 생성 및 데이터 설정
        UserUpdProfileReq p = new UserUpdProfileReq();
        p.setSignedUserNo(signedUserNo);
        p.setPassword(password);
        p.setPasswordConfim(passwordConfim);
        p.setNickname(nickname);
        p.setUserStatusMessage(userStatusMessage != null ? userStatusMessage : ""); // 기본값 처리
        p.setProfilePic(profilePic); // 선택적 파일

        // 비밀번호와 확인 비밀번호 조건 검증
        if (password != null && !password.equals(passwordConfim)) {
            return ResponseResult.badRequest(ResponseCode.INCORRECT_PASSWORD); // 비밀번호 불일치 에러
        }

        // 닉네임, 상태 메시지, 프로필 사진 중 최소 하나가 있어야 수정 가능
        if (nickname == null && userStatusMessage == null && profilePic == null && password == null) {
            return ResponseResult.badRequest(ResponseCode.NO_EXIST_USER); // 수정할 데이터가 없을 경우
        }

        // 서비스 호출
        return service.updUserProfile(p);
    }
}
