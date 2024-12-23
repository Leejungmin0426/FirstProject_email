package com.green.firstproject.project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MemberInfo {
    private long userNo;
    private String nickname;
    private String userProfilePic;
}