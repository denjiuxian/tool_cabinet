package com.loaves.tool_cabinet.util;

import org.junit.jupiter.api.Test;

import javax.crypto.spec.SecretKeySpec;


public class StringUtilsTest {

    @Test
    void dateTest() {

        String name1 = StringUtils.nameSensitiveHide(null);
        String name2 = StringUtils.nameSensitiveHide("张");
        String name3 = StringUtils.nameSensitiveHide("张二");
        String name4 = StringUtils.nameSensitiveHide("张三字");
        String name5 = StringUtils.nameSensitiveHide("张四海棠");
        String name6 = StringUtils.nameSensitiveHide("列夫托尔斯卡");

        String mobile = StringUtils.mobileSensitiveHide("15050438597");

        String idCard = StringUtils.idCardSensitiveHide("342191999271721");

        System.out.println("name1:" + name1);
        System.out.println("name2:" + name2);
        System.out.println("name3:" + name3);
        System.out.println("name4:" + name4);
        System.out.println("name5:" + name5);
        System.out.println("name6:" + name6);

        System.out.println("mobile:" + mobile);
        System.out.println("idCard:" + idCard);

    }

    @Test
    void dateTest2() {
        String value = "欢迎来到我的世界";

        String st1 = EncryptUtils.encryptBase64(value.getBytes());
        System.out.println(st1);
        byte[] st2 = EncryptUtils.decryptBase64(st1);
        System.out.println(new String(st2));

        System.out.println(EncryptUtils.md5(value));
        System.out.println(EncryptUtils.sha256(value));
    }

    @Test
    void dateTest3() {
        String value = "欢迎来到我的世界";
        String st1 = EncryptUtils.encryptAes(value);
        System.out.println("AES加密结果:" + st1);
        String st2 = EncryptUtils.decryptAes(st1);
        System.out.println("AES解密结果:" + st2);
    }

    @Test
    void dateTest4() {
        String value =EncryptUtils.encryptAes("你好啊","hbhxyfb5dsM5hd2S","hbhxopenapi-2021");
        String st2 = EncryptUtils.decryptAes(value,"hbhxyfb5dsM5hd2S","hbhxopenapi-2021");
        System.out.println("AES解密结果:" + st2);

    }


}
