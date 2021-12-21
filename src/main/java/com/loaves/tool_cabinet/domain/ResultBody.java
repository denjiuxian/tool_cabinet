package com.loaves.tool_cabinet.domain;

import com.loaves.tool_cabinet.constant.enums.ResultCode;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * 描述： 接口统一返回包装实体
 *
 * @author LBF
 * @date 2021/3/15 17:46
 **/
@Data
public class ResultBody<T> {
    private Boolean success;
    private Integer retCode;
    private String retMsg;
    private long timeStamp;
    private T data;

    public ResultBody() {
    }

    public ResultBody(boolean success) {
        this.success = success;
        this.retCode = success ? ResultCode.SUCCESS.getCode() : ResultCode.COMMON_FAIL.getCode();
        this.retMsg = success ? ResultCode.SUCCESS.getMessage() : ResultCode.COMMON_FAIL.getMessage();
        this.timeStamp = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }

    public ResultBody(boolean success, ResultCode resultEnum) {
        this.success = success;
        if (success) {
            this.retCode = ResultCode.SUCCESS.getCode();
            this.retMsg = ResultCode.SUCCESS.getMessage();
        } else {
            this.retCode = resultEnum == null ? ResultCode.COMMON_FAIL.getCode() : resultEnum.getCode();
            this.retMsg = resultEnum == null ? ResultCode.COMMON_FAIL.getMessage() : resultEnum.getMessage();
        }
        this.timeStamp = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }

    public ResultBody(boolean success, T data) {
        this.success = success;
        this.retCode = success ? ResultCode.SUCCESS.getCode() : ResultCode.COMMON_FAIL.getCode();
        this.retMsg = success ? ResultCode.SUCCESS.getMessage() : ResultCode.COMMON_FAIL.getMessage();
        this.data = data;
        this.timeStamp = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }

    public ResultBody(boolean success, ResultCode resultEnum, T data) {
        this.success = success;
        if (success) {
            this.retCode = ResultCode.SUCCESS.getCode();
            this.retMsg = ResultCode.SUCCESS.getMessage();
        } else {
            this.retCode = resultEnum == null ? ResultCode.COMMON_FAIL.getCode() : resultEnum.getCode();
            this.retMsg = resultEnum == null ? ResultCode.COMMON_FAIL.getMessage() : resultEnum.getMessage();
        }
        this.data = data;
        this.timeStamp = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }

    public ResultBody(boolean success, String msg) {
        this.success = success;
        this.retCode = success ? ResultCode.SUCCESS.getCode() : ResultCode.COMMON_FAIL.getCode();
        this.retMsg = msg;
        this.timeStamp = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }

    public ResultBody(boolean success, Integer retCode, String msg) {
        this.success = success;
        this.retCode = retCode;
        this.retMsg = msg;
        this.timeStamp = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }

    public Boolean isSuccess() {
        return ResultCode.SUCCESS.getCode().equals(this.retCode);
    }

}
