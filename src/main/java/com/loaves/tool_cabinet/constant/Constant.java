package com.loaves.tool_cabinet.constant;

/**
 * 描述：通用的一些常量
 *
 * @author LBF
 * @date 2021/9/30 16:55
 **/
public class Constant {

    /**
     * 字符串：*（一颗星）
     */
    public static final String ASTERISK_ONE = "*";

    /**
     * 字符串：**（两颗星）
     */
    public static final String ASTERISK_TWO = "**";

    /**
     * 字符串：***（三颗星）
     */
    public static final String ASTERISK_THREE = "***";

    /**
     * 字符串：****（四颗星）
     */
    public static final String ASTERISK_FOUR = "****";

    /**
     * 随机数生成算法
     */
    public static final String RANDOM_INSTANCE = "SHA1PRNG";

    /**
     * 英文逗号
     */
    public static final String EN_COMMA = ",";

    /**
     * 中文逗号
     */
    public static final String CN_COMMA = "，";

    /**
     * 符号"^"，乘方、插入符号、插入符、脱字符
     */
    public static final String CARET = "^";

    private Constant() {throw new IllegalStateException("Constant class");}

}
