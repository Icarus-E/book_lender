package com.piggy.demo.controller.utils;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ProjectExceptionAdvice {
    @ExceptionHandler
    public Result doException(Exception ex){
        ex.printStackTrace();
        return Result.error("服务器故障");
    }
}
