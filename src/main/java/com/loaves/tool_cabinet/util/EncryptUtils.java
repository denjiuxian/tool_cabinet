package com.loaves.tool_cabinet.util;

import com.loaves.tool_cabinet.constant.enums.DigestAlgorithmType;
import com.loaves.tool_cabinet.constant.enums.ResultCode;
import com.loaves.tool_cabinet.exception.BizException;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Optional;

/**
 * 描述：加解密工具方法
 *
 * @author LBF
 * @date 2021/10/8 13:43
 **/
@Slf4j
public class EncryptUtils {

    //===============================BASE64 编码和解码==================================

    /**
     * BASE64 解码
     *
     * @param value 解码前明文
     * @return 解码后密文
     */
    public static byte[] decryptBase64(String value) {
        return StringUtils.isEmpty(value) ? null : Base64.getDecoder().decode(value.getBytes());
    }

    /**
     * BASE64 编码
     *
     * @param key 编码前密文
     * @return 编码后明文
     */
    public static String encryptBase64(byte[] key) {
        return Base64.getEncoder().encodeToString(key);
    }


    private EncryptUtils() {
        throw new IllegalStateException("Encrypt Utility class");
    }

    //===============================MD5,SHA-1,SHA-256 摘要算法==================================

    /**
     * 信息摘要算法（byte[]）
     *
     * @param bytes 信息
     * @param type  类型 --> MD5,SHA-1,SHA-256
     * @return 信息摘要计算结果
     */
    public static String messageDigest(byte[] bytes, DigestAlgorithmType type) {
        try {
            //选择对应的信息摘要算法
            MessageDigest md = MessageDigest.getInstance(type.getType());
            //摘要算法计算结果
            md.update(bytes);
            byte[] b = md.digest();
            //摘要算法结果转化成对应字符串
            int i;
            StringBuilder bu = new StringBuilder();
            for (byte value : b) {
                i = value;
                if (i < 0) {
                    i += 256;
                }
                if (i < 16) {
                    bu.append("0");
                }
                bu.append(Integer.toHexString(i));
            }

            return bu.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 信息摘要算法（String）
     *
     * @param msg  信息
     * @param type 类型
     * @return 信息摘要计算结果
     */
    public static String messageDigest(String msg, DigestAlgorithmType type) {
        return StringUtils.isEmpty(msg) ? null : messageDigest(msg.getBytes(), type);
    }

    /**
     * SHA-256 信息摘要算法
     *
     * @param msg 原信息
     * @return 加密结果
     */
    public static String sha256(String msg) {
        return messageDigest(msg, DigestAlgorithmType.SHA256);
    }

    /**
     * MD5 信息摘要算法
     *
     * @param msg 原信息
     * @return 加密结果
     */
    public static String md5(String msg) {
        return messageDigest(msg, DigestAlgorithmType.MD5);
    }

    //===============================AES 加解密算法==================================

    /**
     * 加解密密钥
     */
    private static final String DEFAULT_KEY = "01234567890hello";
    /**
     * 加密用盐
     */
    private static final String DEFAULT_SALT = "01234567890hello";
    /**
     * 算法/模式/补码方式
     */
    private static final String AES_INSTANCE = "AES/CBC/PKCS5Padding";

    /**
     * 初始化一个密码器
     *
     * @param mode         1:加密，2：解密
     * @param secretKeyStr 加密秘钥，空的时候取默认
     * @param iv           初始化向量（盐）
     * @return 密码加密或解密构造器
     */
    private static Cipher initCipher(int mode, String secretKeyStr, String iv)
            throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException {
        // 获得一个加密规则 SecretKeySpec
        String key = Optional.ofNullable(secretKeyStr).orElse(DEFAULT_KEY);
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");
        // 获得加密算法实例对象 Cipher（"算法/模式/补码方式"）
        Cipher cipher = Cipher.getInstance(AES_INSTANCE);
        // 获得一个 IvParameterSpec
        String siv = Optional.ofNullable(iv).orElse(DEFAULT_SALT);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(siv.getBytes(StandardCharsets.UTF_8));
        // 根据参数初始化算法
        cipher.init(mode, secretKeySpec, ivParameterSpec);
        return cipher;
    }

    /**
     * AES加密算法
     *
     * @param content      待加密内容
     * @param secretKeyStr 秘钥
     * @param iv           偏移量
     * @return 加密结果（密文）
     */
    public static String encryptAes(byte[] content, String secretKeyStr, String iv) {
        try {
            //获取加密器
            Cipher cipher = initCipher(Cipher.ENCRYPT_MODE, secretKeyStr, iv);
            //对象加密操作
            return encryptBase64(cipher.doFinal(content));
        } catch (Exception ex) {
            //统一异常处理
            log.error("AES加密发生异常", ex);
            throw new BizException(ResultCode.INTERNAL_SERVER_ERROR, ex);
        }
    }

    /**
     * AES 加密算法（使用系统默认秘钥与偏移量）
     *
     * @param content 待加密内容
     * @return 加密结果（密文）
     */
    public static String encryptAes(String content) {
        return encryptAes(content.getBytes(StandardCharsets.UTF_8), null, null);
    }

    /**
     * AES 加密算法（使用自定义秘钥与偏移量）
     *
     * @param content      待加密内容
     * @param secretKeyStr 秘钥
     * @param iv           偏移量
     * @return 加密结果（密文）
     */
    public static String encryptAes(String content, String secretKeyStr, String iv) {
        return encryptAes(content.getBytes(StandardCharsets.UTF_8), secretKeyStr, iv);
    }

    /**
     * AES解密算法
     *
     * @param content      待解密内容
     * @param secretKeyStr 秘钥
     * @param iv           偏移量
     * @return 解密结果（明文）
     */
    public static String decryptAes(byte[] content, String secretKeyStr, String iv) {
        try {
            // 密文进行 BASE64 解密处理
            byte[] contentDecByBase64 = Base64.getDecoder().decode(content);
            //获取加密器
            Cipher cipher = initCipher(Cipher.DECRYPT_MODE, secretKeyStr, iv);
            //对象加密操作
            return new String(cipher.doFinal(contentDecByBase64), StandardCharsets.UTF_8);
        } catch (Exception ex) {
            //统一异常处理
            log.error("AES解密发生异常", ex);
            throw new BizException(ResultCode.INTERNAL_SERVER_ERROR, ex);
        }
    }

    /**
     * AES解密算法（使用系统默认秘钥与偏移量）
     *
     * @param content 待解密内容
     * @return 解密结果（明文）
     */
    public static String decryptAes(String content) {
        return decryptAes(content.getBytes(StandardCharsets.UTF_8), null, null);
    }

    /**
     * AES解密算法（使用自定义秘钥与偏移量）
     *
     * @param content      待解密内容
     * @param secretKeyStr 秘钥
     * @param iv           偏移量
     * @return 解密结果
     */
    public static String decryptAes(String content, String secretKeyStr, String iv) {
        return decryptAes(content.getBytes(StandardCharsets.UTF_8), secretKeyStr, iv);
    }

}
