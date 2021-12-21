package com.loaves.tool_cabinet.util;

import com.loaves.tool_cabinet.constant.Constant;
import com.loaves.tool_cabinet.constant.MathConstant;
import lombok.extern.slf4j.Slf4j;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.UUID;

/**
 * 描述：字符串工具类
 *
 * @author LBF
 * @date 2021/9/30 10:20
 **/
@Slf4j
public class StringUtils {

    /**
     * 判断字符创是否为空（null 或 ""）
     *
     * @param str 待判断字符串
     * @return 是否为null或""
     */
    public static boolean isEmpty(String str) {
        return str == null || "".equals(str);
    }

    /**
     * 判断字符串是不是空值（""或全部空格）
     *
     * @param str 待判断字符串
     * @return 是否为""或全部空格
     */
    public static boolean isBlank(String str) {
        return str.replace(" ", "").length() == 0;
    }

    /**
     * 字符串倒转，空字符返回null
     *
     * @param str 待倒转字符串
     * @return 字符串倒转后结果
     */
    public static String reverseString(String str) {
        return str == null ? null : new StringBuffer(str).reverse().toString();
    }

    /**
     * 姓名脱敏处理
     * 规则：2个字脱敏最后一个字，3个字脱敏中间一个字，
     * 4个字脱敏中间两个字，5及以上个字保留第一个和最后两个字
     *
     * @param str 待脱敏的名称
     * @return 脱敏后名称
     */
    public static String nameSensitiveHide(String str) {
        //名称为空或者一个字符串的直接返回
        if (isEmpty(str) || str.length() == MathConstant.NUM_ONE) {
            return str;
        }
        //名称为两个字符串的，脱敏最后一个字
        if (str.length() == MathConstant.NUM_TWO) {
            return str.substring(0, 1) + Constant.ASTERISK_ONE;
        }
        //名称为三个字的，脱敏中间一个字，总字数不变
        if (str.length() == MathConstant.NUM_THREE) {
            return str.substring(0, 1) + Constant.ASTERISK_ONE + str.substring(2, 3);
        }
        //名称为四个字的，脱敏中间两个字，总字数不变
        if (str.length() == MathConstant.NUM_FOUR) {
            return str.substring(0, 1) + Constant.ASTERISK_TWO + str.substring(3, 4);
        }
        //名称为五个字的，脱敏中间两个字，保留前面一个字和后面两个字
        if (str.length() == MathConstant.NUM_FIVE) {
            return str.substring(0, 1) + Constant.ASTERISK_TWO + str.substring(3, 5);
        }
        //名称为大于五个字的，脱敏中间的字，保留前面一个字和后面两个字
        return str.substring(0, 1) + Constant.ASTERISK_THREE + str.substring(str.length() - 2);
    }

    /**
     * 手机号脱敏
     * <p>
     * 手机号前三位+后四位保留，中间位置脱敏
     * 例如：187****1234
     *
     * @param str 手机号码
     * @return 脱敏后手机号码
     */
    public static String mobileSensitiveHide(String str) {
        //手机号为空或者一个字符串的直接返回
        if (isEmpty(str) || str.length() == MathConstant.NUM_ONE) {
            return str;
        }

        //手机号前三位+后四位保留，中间位置脱敏
        return str.substring(0, 3) + Constant.ASTERISK_FOUR + str.substring(str.length() - 4);
    }

    /**
     * 身份证号码脱敏
     * 保留前六后三
     *
     * @param idNumber 身份证号码（15位或者18位）
     * @return 身份证号码脱敏，310102******123
     */
    public static String idCardSensitiveHide(String idNumber) {
        if (!isEmpty(idNumber)) {
            //15位身份证号码的场合
            if (idNumber.length() == MathConstant.NUM_FIFTEEN) {
                idNumber = idNumber.replaceAll("(\\w{4})\\w*(\\w{3})", "$1********$2");
            }
            //18位身份证号码的场合
            if (idNumber.length() == MathConstant.NUM_EIGHTEEN) {
                idNumber = idNumber.replaceAll("(\\w{4})\\w*(\\w{3})", "$1***********$2");
            }
        }
        return idNumber;
    }

    /**
     * 获取32位UUID
     *
     * @return 32位UUID
     */
    public static String handOutId() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 生成4位数随机数，不足4位前端补0
     *
     * @return 4位数随机数
     */
    public static String createFourDigitRandom() {
        try {
            SecureRandom random = SecureRandom.getInstance(Constant.RANDOM_INSTANCE);
            return String.format("%04d", random.nextInt(9999));
        } catch (NoSuchAlgorithmException e) {
            log.error("生成4位数随机数失败", e);
            return null;
        }
    }

    /**
     * 生成6位数随机数，不足6位前面补0
     *
     * @return 4位数随机数
     */
    public static String createSixDigitRandom() {
        try {
            SecureRandom random = SecureRandom.getInstance(Constant.RANDOM_INSTANCE);
            return String.format("%06d", random.nextInt(999999));
        } catch (NoSuchAlgorithmException e) {
            log.error("生成6位数随机数失败", e);
            return null;
        }
    }

    private StringUtils() {
        throw new IllegalStateException("String Utility class");
    }

}
