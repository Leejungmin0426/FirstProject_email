package com.green.firstproject.user;

import com.green.firstproject.user.model.*;
import com.green.firstproject.user.model.dto.UserInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    UserInfo userSignIn(String email);
    UserInfoGetRes selUserInfo(UserInfoGetReq p);
    UserUpdProfilePageRes getUdpProfilePage (UserUpdProfilePageReq p);

    boolean checkNicknameExists(String nickname);   // 닉네임 중복 체크
    int updUserProfile(UserUpdProfileReq p);    // 사용자 정보 업데이트
}
