package com.green.firstproject.user;

import com.green.firstproject.common.MyFileUtils;
import com.green.firstproject.common.ResponseCode;
import com.green.firstproject.common.ResponseResult;
import com.green.firstproject.user.model.*;
import com.green.firstproject.user.model.dto.UserInfo;
import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import java.util.UUID;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper mapper;
    private final MyFileUtils myFileUtils;


    // 1. 사용자 로그인
    public ResponseResult userSignIn(UserSignInReq p) {
        // 요청 데이터 검증
        if (p == null || p.getEmail() == null || p.getEmail().isEmpty()) {
            return ResponseResult.incorrectEmail(); // 이메일이 없거나 비어 있을 경우
        }

        if (p.getPassword() == null || p.getPassword().isEmpty()) {
            return ResponseResult.incorrectPassword(); // 비밀번호가 없거나 비어 있을 경우
        }

        // 매퍼 메서드를 호출하여 사용자 조회
        UserInfo info = mapper.userSignIn(p.getEmail());
        if (info == null) {
            return ResponseResult.noExistUser(); // 사용자 정보가 없을 경우
        }

        // 비밀번호 검증
        if (!BCrypt.checkpw(p.getPassword(), info.getPassword())) {
            System.out.println("입력된 비밀번호: " + p.getPassword());
            System.out.println("DB 저장된 해시 비밀번호: " + info.getPassword());
            return ResponseResult.incorrectPassword(); // 비밀번호 불일치
        }

        // 첫 로그인 여부 확인
        boolean firstLogin = info.isFirstLogin(); // DB에서 firstLogin 여부 가져옴

        // 성공 응답
        return new UserSignInRes(ResponseCode.OK.getCode(), firstLogin);
    }



    public ResponseResult selUserInfo(UserInfoGetReq p) {
        // 요청 데이터 유효성 검사
        if (p.getSignedUserNo() <= 0 || p.getProfileUserNo() <= 0) {
            return ResponseResult.noExistUser(); // 잘못된 요청
        }

        // 두 값이 같은지 확인
        if (p.getSignedUserNo() != p.getProfileUserNo()) {
            return ResponseResult.noPermission(); // 권한 없음 응답
        }

        // 사용자 정보 조회
        UserInfoGetRes res = mapper.selUserInfo(p);
        if (res == null) {
            return ResponseResult.noExistUser(); // 사용자 정보 없음
        }

        // 성공 응답 생성
        return new UserInfoGetRes(ResponseCode.OK.getCode(), res.getUserInfo());
    }



    @Transactional
    public ResponseResult updUserProfile(UserUpdProfileReq p) {
        // 요청 데이터 검증
        if (p == null) {
            return ResponseResult.noExistUser(); // 요청 데이터가 없을 경우
        }

        // 비밀번호 형식 검증
        if (!isValidPasswordFormat(p.getPassword())) {
            return ResponseResult.passwordFormatError(); // 비밀번호 형식 에러
        }

        // 비밀번호 확인 체크
        if (!p.getPassword().equals(p.getPasswordConfim())) {
            return ResponseResult.passwordCheckError(); // 비밀번호 확인 에러
        }

        // 닉네임 중복 체크
        if (mapper.checkNicknameExists(p.getNickname())) {
            return ResponseResult.duplicateNickname(); // 닉네임 중복 에러
        }

        // 프로필 사진 처리
        String savedPicName = UUID.randomUUID().toString(); // 고유한 파일 이름 생성
        String filePath = String.format("user/%d/%s", p.getUserNo(), savedPicName);

        try {
            myFileUtils.transferTo(p.getProfilePic(), filePath);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseResult.serverError(); // 파일 저장 실패 시 서버 에러 반환
        }

        // 비밀번호 암호화 및 DB 업데이트
        p.setPassword(BCrypt.hashpw(p.getPassword(), BCrypt.gensalt())); // 비밀번호 암호화
        p.setProfilePicName(savedPicName); // 저장된 파일명을 DB에 반영

        int updatedRows = mapper.updUserProfile(p);
        if (updatedRows <= 0) {
            return ResponseResult.serverError(); // 업데이트 실패 시 서버 에러 반환
        }

        return ResponseResult.success(); // 성공 메시지
    }

    // 4. 프로필 사진 처리
    private ResponseResult patchUserPic(MultipartFile profilePic, String email) throws IOException {
        String savedPicName = myFileUtils.makeRandomFileName(profilePic); // 랜덤 파일명 생성
        String folderPath = String.format("user/%s", email); // 폴더 경로 생성
        myFileUtils.makeFolders(folderPath); // 폴더 생성

        // 기존 파일 삭제
        String deletePath = String.format("%s/%s", myFileUtils.getUploadPath(), folderPath);
        myFileUtils.deleteFolder(deletePath, false);

        // 새 파일 저장
        String filePath = String.format("%s/%s", folderPath, savedPicName);
        myFileUtils.transferTo(profilePic, filePath);

        return ResponseResult.success();
    }

    // 5. 비밀번호 형식 검증
    private boolean isValidPasswordFormat(String password) {
        if (password == null) return false;
        return password.matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,16}$");
    }


    public ResponseResult getUpdProfilePage(UserUpdProfilePageReq p) {
        // 요청 데이터 검증
        if (p == null || p.getSignedUserNo() <= 0 || p.getProfileUserNo() <= 0) {
            return ResponseResult.noExistUser(); // 잘못된 요청 데이터 처리
        }

        // 사용자 프로필 페이지 데이터 조회
        UserUpdProfilePageRes res = mapper.getUdpProfilePage(p); // mapper를 통해 데이터베이스 조회

        // 권한 검증
        if (p.getSignedUserNo() != p.getProfileUserNo()) {
            return ResponseResult.noPermission(); // 권한 없음
        }

        // 성공 응답 반환
        return res;
    }
}