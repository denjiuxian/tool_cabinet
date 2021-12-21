package com.loaves.tool_cabinet.util;

import com.google.zxing.WriterException;
import com.loaves.tool_cabinet.constant.VerifyCodeKeyConstant;
import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

public class GenerationCodeUtilsTest {
    @Test
    void dateTest() throws IOException {
        Map<String, Object> map = GenerationCodeUtils.generateCodeAndPic();

        File file = new File("E:\\test\\" + UUID.randomUUID() + ".jpeg");
        GenerationCodeUtils.output(
                (BufferedImage) map.get(VerifyCodeKeyConstant.KEY_CODE_PIC)
                , new FileOutputStream(file));
    }

    @Test
    void dateTest1() throws IOException, WriterException {
        String count = "https://www.jianshu.com/p/bb76ded47d64";
        String u = "E:\\test\\tx.jpeg";
        BufferedImage bufferedImage = GenerationCodeUtils.createQrCode(count,u,true);
        File file = new File("E:\\test\\" + UUID.randomUUID() + ".jpeg");
        GenerationCodeUtils.output(
                bufferedImage
                , new FileOutputStream(file));

        BufferedImage bufferedImage2 = GenerationCodeUtils.createQrCode(count,null,false);
        File file2 = new File("E:\\test\\" + UUID.randomUUID() + ".jpeg");
        GenerationCodeUtils.output(
                bufferedImage2
                , new FileOutputStream(file2));
    }

}
