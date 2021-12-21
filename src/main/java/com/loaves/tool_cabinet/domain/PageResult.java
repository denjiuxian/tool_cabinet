package com.loaves.tool_cabinet.domain;

import com.loaves.tool_cabinet.constant.enums.ResultCode;
import lombok.Data;

import java.util.List;

/**
 * 描述：分页数据返回封装
 *
 * @author LBF
 * @date 2021/6/17 16:17
 **/
@Data
public class PageResult<T> {
    /**
     * 成功状态
     */
    private Boolean success;
    /**
     * 状态码
     */
    private Integer retCode;
    /**
     * 提示信息
     */
    private String retMsg;
    /**
     * 分页总数
     */
    private Long count;
    /**
     * 分页数据
     */
    private List<T> data;

    PageResult(List<T> data, Long count) {
        this.success = true;
        this.retCode = ResultCode.SUCCESS.getCode();
        this.retMsg = ResultCode.SUCCESS.getMessage();
        this.data = data;
        this.count = count;
    }

    PageResult(Integer retCode, String retMsg) {
        this.success = false;
        this.retCode = retCode;
        this.retMsg = retMsg;
    }
}
