package com.green.firstproject.user;

import com.green.firstproject.user.model.*;
import com.green.firstproject.user.model.dto.UserInfo;
import com.green.firstproject.user.model.dto.UserLoginInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    UserLoginInfo userSignIn(String email);
    UserInfo selUserInfo(UserInfoGetReq p);


    boolean checkNicknameExists(String nickname);   // 닉네임 중복 체크
    int updUserProfile(UserUpdProfileReq p);    // 사용자 정보 업데이트
}
