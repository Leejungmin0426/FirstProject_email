package com.green.firstproject.user;

import com.green.firstproject.user.model.*;
import com.green.firstproject.user.model.dto.UserInfo;
import com.green.firstproject.user.model.dto.UserLoginInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface UserMapper {
    UserLoginInfo userSignIn(String email);
    UserInfo selUserInfo (UserInfoGetReq p);
}
