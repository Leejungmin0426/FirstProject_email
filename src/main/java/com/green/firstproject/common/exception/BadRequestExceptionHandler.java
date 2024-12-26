package com.green.firstproject.common.exception;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BadRequestExceptionHandler {

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseResult notNullExceptionHandler(MethodArgumentNotValidException e) {
        return ResponseResult.badRequest(ResponseCode.NOT_NULL);
    }



}