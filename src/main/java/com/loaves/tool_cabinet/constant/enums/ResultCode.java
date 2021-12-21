package com.loaves.tool_cabinet.constant.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 描述：接口返回状态码
 *
 * @author LBF
 * @date 2021/3/19 11:19
 **/
@Getter
@AllArgsConstructor
public enum ResultCode {
    /* 操作代码 */
    SUCCESS(200, "操作成功!"),
    COMMON_FAIL(-100, "操作失败!"),
    BODY_NOT_MATCH(400, "请求的数据格式不符!"),
    SIGNATURE_NOT_MATCH(401, "请求的数字签名不匹配!"),
    NOT_FOUND(404, "未找到该资源!"),
    PREREQUISITE_FAILED(412, "先决条件失败!"),
    INTERNAL_SERVER_ERROR(500, "服务器内部错误!"),
    SERVER_BUSY(503, "服务器正忙，请稍后再试!");

    private final Integer code;
    private final String message;


    /**
     * 根据code获取message
     *
     * @param code code
     * @return message
     */
    public static String getMessageByCode(Integer code) {
        for (ResultCode ele : values()) {
            if (ele.getCode().equals(code)) {
                return ele.getMessage();
            }
        }
        return null;
    }

}
