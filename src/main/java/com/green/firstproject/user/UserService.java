package com.green.firstproject.user;

import com.green.firstproject.common.MyFileUtils;
import com.green.firstproject.common.ResponseResult;
import com.green.firstproject.user.model.*;
import com.green.firstproject.user.model.dto.UserInfo;
import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper mapper;
    private final MyFileUtils myFileUtils;

    // 1. 사용자 로그인
    public ResponseResult selUserByUid(UserSignInReq p) {
        // 이메일로 사용자 조회
        UserInfo info = mapper.selUserByEmail(p.getEmail());

        if (p == null) {
            return new UserSignInRes(405, "필수사항을 미기재하셨습니다.");
        }

        if (p.getEmail() == null) {
            return ResponseResult.badRequest("이메일을 작성해 주세요.");
        }

        if(p.getPassword() == null) {
            return ResponseResult.badRequest("비밀번호를 작성해 주세요.");
        }

        // 비밀번호 검증
        if (!BCrypt.checkpw(p.getPassword(), info.getPassword())) {
            return new UserSignInRes(406, "비밀번호가 맞지 않습니다");
        }

        // 로그인 성공
        return new UserSignInRes(200, "OK");
    }






    // 2. 사용자 정보 조회
    public ResponseResult selUserInfo(UserInfoGetReq p) {
        UserInfoGetRes res = mapper.selUserInfo(p);



        return ResponseResult.success("OK");
    }



    // 3. 사용자 정보 업데이트 (프로필 포함)
    @Transactional
    public ResponseResult updUserProfile(UserUpdProfileReq p) {

        // 비밀번호 형식 검증
        if (!isValidPasswordFormat(p.getPassword())) {
            return ResponseResult.badRequest("비밀번호 형식이 맞지 않습니다.");
        }

        // 비밀번호 확인 체크
        if (!p.getPassword().equals(p.getPasswordConfim())) {
            return ResponseResult.badRequest("비밀번호 체크 오류 입니다.");
        }

        // 닉네임 중복 체크
        if (mapper.checkNicknameExists(p.getNickname())) {
            return ResponseResult.badRequest("닉네임이 이미 존재합니다");
        }

        // 프로필 사진 처리
        String savedPicName = null;
        String filePath = String.format("user/%d/%s", p.getProfilePicName(), savedPicName);

        try {
            myFileUtils.transferTo(p.getProfilePic(), filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }


        // 사용자 정보 DB 업데이트
        p.setPassword(BCrypt.hashpw(p.getPassword(), BCrypt.gensalt())); // 비밀번호 암호화
        p.setProfilePicName(savedPicName); // 저장된 파일명을 DB에 반영
        int result = mapper.updUserProfile(p);

        if (result <= 0) {
            return ResponseResult.serverError();
        }

        return ResponseResult.success("사용자 정보가 성공적으로 업데이트 되었습니다.");
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

        return ResponseResult.success("사진을 성공적으로 업데이트 하였습니다.");
    }

    // 5. 비밀번호 형식 검증
    private boolean isValidPasswordFormat(String password) {
        if (password == null) return false;
        return password.matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,16}$");
    }



    public ResponseResult getUpdProfilePage(UserUpdProfilePageReq p) {
        UserUpdProfilePageRes res = mapper.getUdpProfilePage(p);
        // signedUserNo와 profileUserNo 비교
        if (res.getSignedUserNo() != res.getProfileUserNo()) {
            // 권한이 없는 경우 null 반환
            return null;
        }

        return ResponseResult.success("OK");
    }
}

