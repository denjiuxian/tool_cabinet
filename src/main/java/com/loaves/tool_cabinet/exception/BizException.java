package com.loaves.tool_cabinet.exception;

import com.loaves.tool_cabinet.constant.enums.ResultCode;
import lombok.extern.slf4j.Slf4j;

/**
 * 描述：系统自定义异常
 *
 * @author LBF
 * @date 2021/10/14 14:41
 **/
@Slf4j
public class BizException extends RuntimeException {
    private static final long serialVersionUID = -6979901566637669960L;

    public Integer code;
    public String message;

    BizException() {
        super();
    }

    public BizException(int code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public BizException(ResultCode exceptionEnum) {
        super(exceptionEnum.getMessage());
        this.code = exceptionEnum.getCode();
    }


    public BizException(int code, String message, Throwable throwable) {
        super(message, throwable);
        this.code = code;
        this.message = message;
    }

    public BizException(ResultCode exceptionEnum, Throwable throwable) {
        super(exceptionEnum.getMessage(), throwable);
        this.message = exceptionEnum.getMessage();
        this.code = exceptionEnum.getCode();
    }

    /**
     * 重写Throwable中printStackTrace方法，打印异常信息
     */
    @Override
    public void printStackTrace() {
        log.info("异常代码: {}, 异常信息: {}", code, message);
    }

}
