package com.green.firstproject.user;

import com.green.firstproject.common.MyFileUtils;
import com.green.firstproject.common.ResponseCode;
import com.green.firstproject.common.ResponseResult;
import com.green.firstproject.user.model.*;
import com.green.firstproject.user.model.dto.UserInfo;
import com.green.firstproject.user.model.dto.UserLoginInfo;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserMapper mapper;

    /**
     * 요청 데이터 유효성 검증 메서드
     */
    private boolean isValidUserRequest(UserInfoGetReq p) {
        // signedUserNo와 targetUserNo가 0보다 큰지 확인
        return p != null && p.getSignedUserNo() > 0 && p.getTargetUserNo() > 0;
    }

    private boolean isUserLoggedIn(long signedUserNo) {
        // PK가 0보다 크면 로그인된 것으로 간주
        return signedUserNo > 0;
    }

    // 1. 사용자 로그인
    public ResponseResult userSignIn(UserSignInReq p) {
        // 요청 데이터 검증
        if (p == null || p.getEmail() == null || p.getEmail().isEmpty()) {
            return ResponseResult.badRequest(ResponseCode.INCORRECT_EMAIL); // 이메일이 없거나 비어 있을 경우
        }

        if (p.getPassword() == null || p.getPassword().isEmpty()) {
            return ResponseResult.badRequest(ResponseCode.INCORRECT_PASSWORD); // 비밀번호가 없거나 비어 있을 경우
        }

        // 매퍼 메서드를 호출하여 사용자 조회
        UserLoginInfo info = mapper.userSignIn(p.getEmail());
        if (info == null) {
            return ResponseResult.badRequest(ResponseCode.NO_EXIST_USER); // 사용자 정보가 없을 경우
        }

        // 비밀번호 검증
        if (!BCrypt.checkpw(p.getPassword(), info.getPassword())) {
            System.out.println("입력된 비밀번호: " + p.getPassword());
            System.out.println("DB 저장된 해시 비밀번호: " + info.getPassword());
            return ResponseResult.badRequest(ResponseCode.INCORRECT_PASSWORD); // 비밀번호 불일치
        }

        // 첫 로그인 여부 확인
        boolean firstLogin = info.isFirstLogin(); // DB에서 firstLogin 여부 가져옴

        // 성공 응답
        return new UserSignInRes(ResponseCode.OK.getCode(), firstLogin);
    }


    @Transactional
    public ResponseResult selUserInfo(UserInfoGetReq p) {
        log.debug("Service Request: {}", p);

        // 1. 로그인 여부 검증
        if (!isUserLoggedIn(p.getSignedUserNo())) {
            log.warn("Unauthorized access: signedUserNo={}", p.getSignedUserNo());
            return ResponseResult.badRequest(ResponseCode.NO_FORBIDDEN); // 로그인하지 않은 사용자
        }

        // 2. 데이터 유효성 검증
        if (!isValidUserRequest(p)) {
            log.warn("Invalid request parameters: {}", p);
            return ResponseResult.badRequest(ResponseCode.NOT_NULL); // 필수 값 검증 실패
        }

        // 3. 사용자 정보 조회
        UserInfo userInfo = mapper.selUserInfo(p);
        if (userInfo == null) {
            log.warn("User not found for signedUserNo={} or targetUserNo={}",
                    p.getSignedUserNo(), p.getTargetUserNo());
            return ResponseResult.badRequest(ResponseCode.NO_EXIST_USER); // 유저 정보 없음
        }

        // 4. 본인 여부 확인
        boolean isMyInfo = (p.getSignedUserNo() == p.getTargetUserNo());
        if (isMyInfo) {
            log.debug("User requested their own information. signedUserNo={}, targetUserNo={}",
                    p.getSignedUserNo(), p.getTargetUserNo());
        } else {
            log.debug("User requested another user's information. signedUserNo={}, targetUserNo={}",
                    p.getSignedUserNo(), p.getTargetUserNo());
        }

        // 5. UserInfoGetRes 반환
        UserInfoGetRes response = new UserInfoGetRes();
        response.setEmail(userInfo.getEmail());
        response.setNickname(userInfo.getNickname());
        response.setUserStatusMessage(userInfo.getUserStatusMessage());
        response.setProfilePic(userInfo.getProfilePic());

        // 본인 여부를 결과에 포함
        response.setMyInfo(isMyInfo);

        return response;
    }
}