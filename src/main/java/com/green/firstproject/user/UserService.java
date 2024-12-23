package com.green.firstproject.user;

import com.green.firstproject.common.MyFileUtils;
import com.green.firstproject.common.ResponseCode;
import com.green.firstproject.common.ResponseResult;
import com.green.firstproject.user.model.*;
import com.green.firstproject.user.model.dto.PasswordUtils;
import com.green.firstproject.user.model.dto.UserInfo;
import com.green.firstproject.user.model.dto.UserLoginInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import java.util.UUID;

import java.io.IOException;

import static com.green.firstproject.user.model.dto.PasswordUtils.isValidPasswordFormat;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserMapper mapper;
    private final MyFileUtils myFileUtils;


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

        // MyBatis 매퍼에서 UserInfo 객체 반환
        UserInfo userInfo = mapper.selUserInfo(p);
        log.debug("Mapper Result: {}", userInfo);

        // 매퍼 결과가 null이면 사용자 정보가 없음을 반환
        if (userInfo == null) {
            return ResponseResult.badRequest(ResponseCode.NO_EXIST_USER);
        }

        // UserInfo를 UserInfoGetRes로 래핑하여 반환
        return new UserInfoGetRes(ResponseCode.OK.getCode(), userInfo);
    }


    @Transactional
    public ResponseResult updUserProfile(UserUpdProfileReq p) {
        // 1. 비밀번호 검증
        if (p.getPassword() != null && !p.getPassword().equals(p.getPasswordConfim())) {
            return ResponseResult.badRequest(ResponseCode.INCORRECT_PASSWORD); // 비밀번호 불일치
        }

        // 2. 닉네임 중복 체크
        if (p.getNickname() != null) {
            Boolean isNicknameExists = mapper.checkNicknameExists(p.getNickname());
            if (isNicknameExists != null && isNicknameExists) {
                return ResponseResult.badRequest(ResponseCode.DUPLICATE_NICKNAME); // 닉네임 중복
            }
        }

        // 3. 프로필 사진 처리
        if (p.getProfilePic() != null && !p.getProfilePic().isEmpty()) {
            try {
                String userFolder = String.format("user/%d", p.getSignedUserNo());

                // 기존 폴더 삭제 후 새 폴더 생성
                myFileUtils.deleteFolder(myFileUtils.getUploadPath() + "/" + userFolder, false);
                myFileUtils.makeFolders(userFolder);

                // 랜덤 파일 이름 생성 및 파일 저장
                String randomFileName = myFileUtils.makeRandomFileName(p.getProfilePic());
                String filePath = String.format("%s/%s", userFolder, randomFileName);
                myFileUtils.transferTo(p.getProfilePic(), filePath);

                // 저장된 파일명을 설정
                p.setProfilePicName(randomFileName);

            } catch (IOException e) {
                log.error("Failed to save profile picture for signedUserNo: {}", p.getSignedUserNo(), e);
                return ResponseResult.serverError(); // 파일 저장 실패
            }
        }

        // 4. 비밀번호 암호화
        if (p.getPassword() != null) {
            p.setPassword(BCrypt.hashpw(p.getPassword(), BCrypt.gensalt())); // 비밀번호 암호화
        }

        // 5. 데이터베이스 업데이트
        int updatedRows = mapper.updUserProfile(p);
        if (updatedRows <= 0) {
            log.error("Failed to update user profile for signedUserNo: {}", p.getSignedUserNo());
            return ResponseResult.serverError(); // 업데이트 실패
        }

        // 6. 성공 응답 반환
        return ResponseResult.success();
    }

}