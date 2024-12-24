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
            @RequestParam("signed_user_no") long signedUserNo,
            @RequestParam("date") String date
    ) {
        log.info("Fetching home screen for signedUserNo={} on date={}", signedUserNo, date);
        return homeService.getHome(signedUserNo, date);
    }
}