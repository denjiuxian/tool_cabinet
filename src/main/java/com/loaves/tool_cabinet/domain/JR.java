package com.loaves.tool_cabinet.domain;

import com.loaves.tool_cabinet.constant.enums.ResultCode;

import java.util.List;

/**
 * 描述：返回体构造工具
 *
 * @author LBF
 * @date 2021/12/3 10:19
 **/
public class JR {

    public static <T> ResultBody<T> success() {
        return new ResultBody<>(true);
    }

    public static <T> ResultBody<T> success(T data) {
        return new ResultBody<>(true, data);
    }

    public static <T> ResultBody<T> fail() {
        return new ResultBody<>(false);
    }

    public static <T> ResultBody<T> fail(ResultCode resultEnum) {
        return new ResultBody<>(false, resultEnum);
    }

    public static <T> ResultBody<T> fail(String errMsg) {
        return new ResultBody<>(false, errMsg);
    }

    public static <T> ResultBody<T> successMsg(String msg) {
        return new ResultBody<>(true, msg);
    }

    public static <T> ResultBody<T> fail(ResultCode resultEnum, String msg) {
        return new ResultBody<>(false, resultEnum.getCode(), msg);
    }

    public static <T> PageResult<T> success(List<T> data, Long count) {
        return new PageResult<>(data, count);
    }

    public static <T> PageResult<T> failPage(Integer retCode, String retMsg) {
        return new PageResult<>(retCode, retMsg);
    }

}
