package com.piggy.demo.controller.utils;

import lombok.Data;

@Data
public class Result {
    private boolean flag;
    private Object data;
    private String msg;

    public static Result success(Object successData,String successMsg){
        Result result = new Result();
        result.flag = true;
        result.data = successData;
        result.msg = successMsg;
        return result;
    }
    public static Result error(String errorMsg){
        Result result = new Result();
        result.flag = false;
        result.data = null;
        result.msg = errorMsg;
        return result;
    }
}
