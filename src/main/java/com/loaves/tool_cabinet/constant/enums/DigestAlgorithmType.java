package com.loaves.tool_cabinet.constant.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 描述：信息摘要算法类型
 *
 * @author LBF
 * @date 2021/10/8 15:40
 **/
@Getter
@AllArgsConstructor
public enum DigestAlgorithmType {

    /**
     * 信息摘要算法类型.
     */
    MD5("MD5"),
    SHA1("SHA-1"),
    SHA256("SHA-256");

    private final String type;

}
