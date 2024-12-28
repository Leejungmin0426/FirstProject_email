package com.green.firstproject.email;

import com.green.firstproject.common.exception.ResponseCode;
import com.green.firstproject.common.exception.ResponseResult;
import com.green.firstproject.email.model.User;
import com.green.firstproject.email.model.UserRepository;
import com.green.firstproject.email.model.VerificationToken;
import com.green.firstproject.email.model.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/mail")
public class EmailController {

    @Autowired
    private EmailServiceImpl emailService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VerificationTokenRepository tokenRepository;

    // 이메일 인증 요청
    @PostMapping
    public String registerUser(@RequestParam String email, @RequestParam(required = false) String provider) {
        // 이메일 중복 확인
        if (userRepository.findByEmail(email) != null) {
            return "이미 등록된 이메일입니다.";
        }

        // 사용자 생성 및 저장
        User user = new User();
        user.setEmail(email);
        user.setEnabled(1); // 비활성 상태로 저장
        userRepository.save(user);

        // 토큰 생성 및 저장
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setUserNo(verificationToken.getUserNo());
        verificationToken.setExpiryDate(LocalDateTime.now().plusHours(24)); // 24시간 유효
        tokenRepository.save(verificationToken);

        // 인증 링크 생성 및 이메일 발송
        String verificationLink = "http://localhost:8080/mail/verify?token=" + token;

        // 이메일 제공자 설정: 기본값은 Gmail
        String emailProvider = (provider != null && !provider.isEmpty()) ? provider : "gmail";

        try {
            emailService.sendEmail(email, "이메일 인증", "인증 링크: " + verificationLink, emailProvider);
        } catch (Exception e) {
            return "이메일 발송 실패: " + e.getMessage();
        }

        return "인증 이메일이 발송되었습니다.";
    }

    /**
     * 이메일 인증 API
     */
    @GetMapping("/verify")
    public String verifyEmail(@RequestParam String token) {
        // 이메일 인증 로직
        VerificationToken verificationToken = tokenRepository.findByToken(token);
        if (verificationToken == null) {
            return "유효하지 않은 토큰입니다.";
        }

        if (verificationToken.getExpiryDate().isBefore(LocalDateTime.now())) {
            return "토큰이 만료되었습니다.";
        }

        User user = userRepository.findByEmail(verificationToken.getEmail());
        if (user == null) {
            return "해당 이메일의 사용자가 존재하지 않습니다.";
        }

        user.setEnabled(1); // 계정 활성화
        userRepository.save(user);
        tokenRepository.delete(verificationToken);

        return "이메일 인증이 완료되었습니다.";
    }
}