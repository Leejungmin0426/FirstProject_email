package com.green.firstproject.user.model.dto;

import com.green.firstproject.user.model.UserSignUpReq;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DuplicateCheckResult {
    private int emailCount;
    private int userIdCount;
    private int nicknameCount;
}
