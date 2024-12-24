package com.green.firstproject.home;

import com.green.firstproject.common.ResponseResult;
import com.green.firstproject.home.model.HomeGetReq;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("main")
@RequiredArgsConstructor
public class HomeController {

    private final HomeService homeService;

    @GetMapping
    public ResponseResult getHome(
            @RequestParam("signed_user_no") long signedUserNo
    ) {
        log.info("Fetching home screen for signedUserNo={}", signedUserNo);
        return homeService.getHome(signedUserNo);
    }
}