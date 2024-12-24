package com.green.firstproject;

import com.green.firstproject.user.UserMapper;
import com.green.firstproject.user.model.UserInfoGetReq;
import com.green.firstproject.user.model.UserInfoGetRes;
import com.green.firstproject.user.model.dto.UserInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class FirstProjectApplicationTests {

    @Autowired
    private UserMapper mapper; // UserMapper 주입

    @Test
    public void testSelUserInfo() {
        // Given
        UserInfoGetReq request = new UserInfoGetReq(1L, 1L);

        // When
        UserInfo result = mapper.selUserInfo(request);

        // Then
        assertNotNull(result); // 결과가 null이 아님을 확인
        assertEquals("expected-email@example.com", result.getEmail()); // email이 예상값과 일치하는지 확인
    }
}