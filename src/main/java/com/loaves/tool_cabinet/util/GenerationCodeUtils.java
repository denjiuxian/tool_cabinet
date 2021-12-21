package com.loaves.tool_cabinet.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.loaves.tool_cabinet.constant.VerifyCodeKeyConstant;
import lombok.extern.slf4j.Slf4j;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

/**
 * 描述：各种生成码校验与生成
 *
 * @author LBF
 * @date 2021/10/14 17:18
 **/
@Slf4j
public class GenerationCodeUtils {
    //==================================图片验证码（数字计算类型）==================================
    /**
     * 定义图片的width
     */
    private static final int IMG_WIDTH = 165;
    /**
     * 定义图片的height
     */
    private static final int IMG_HEIGHT = 45;
    /**
     * 定义图片上显示验证码的个数
     */
    private static final char[] CODE_SEQUENCE = {'+', '-', '×', '=', '?'};
    /**
     * 随机数生成
     */
    private static final SecureRandom RANDOM_GENERATOR = new SecureRandom();

    /**
     * 图片验证码生成的方法 返回一个map集合（包含：验证码图片流，验证码结果）
     * code为生成的验证码
     * codePic为生成的验证码BufferedImage对象
     *
     * @return 验证码+图片流
     */
    public static Map<String, Object> generateCodeAndPic() {
        // 定义图像buffer
        BufferedImage buffImg = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics2D gd = (Graphics2D) buffImg.getGraphics();
        // 将图像填充为白色
        gd.setColor(Color.WHITE);
        gd.fillRect(0, 0, IMG_WIDTH, IMG_HEIGHT);

        // 创建字体，字体的大小应该根据图片的高度来定。
        Font font = new Font("微软雅黑", Font.BOLD, 36);
        // 设置字体。
        gd.setFont(font);
        //画圆形干扰图(2~4个)
        drawInterfereOval(gd, RANDOM_GENERATOR.nextInt(2) + 2);
        //画矩形干扰图(1~3个)
        drawInterfereRect(gd, RANDOM_GENERATOR.nextInt(1) + 2);

        //第一个数字
        int firstNum = RANDOM_GENERATOR.nextInt(9);
        //第一个数字
        int secondNum = RANDOM_GENERATOR.nextInt(9);
        //产生运算符（0:加法，1:减法，2:乘法）
        int operator = RANDOM_GENERATOR.nextInt(2);

        Object[] codes = {
                firstNum
                , CODE_SEQUENCE[operator]
                , secondNum
                , CODE_SEQUENCE[3]
                , CODE_SEQUENCE[4]};
        int x = 10;
        for (Object code : codes) {
            // 用随机产生的颜色将验证码绘制到图像中。
            gd.setColor(new Color(RANDOM_GENERATOR.nextInt(255),
                    RANDOM_GENERATOR.nextInt(255), RANDOM_GENERATOR.nextInt(255)));
            // 设置字体旋转角度 角度小于20度
            int degree = RANDOM_GENERATOR.nextInt() % 20;
            // 正向旋转
            gd.rotate(degree * Math.PI / 180, x, 45);
            gd.drawString(code.toString(), x, 36);
            // 反向旋转
            gd.rotate(-degree * Math.PI / 180, x, 45);
            x += 30;
        }

        // 计算出验证吗结果.
        String retCode;
        switch (operator) {
            case 0:
                retCode = (firstNum + secondNum) + "";
                break;
            case 1:
                retCode = (firstNum - secondNum) + "";
                break;
            case 2:
                retCode = (firstNum * secondNum) + "";
                break;
            default:
                retCode = "";
        }

        Map<String, Object> map = new HashMap<>(2);
        //存放验证码
        map.put(VerifyCodeKeyConstant.KEY_CODE, retCode);
        //存放生成的验证码BufferedImage对象
        map.put(VerifyCodeKeyConstant.KEY_CODE_PIC, buffImg);
        gd.dispose();
        return map;
    }

    /**
     * 画圆形干扰图
     *
     * @param gd     Graphics2D 对象
     * @param number 画圆数量
     */
    private static void drawInterfereOval(Graphics2D gd, int number) {
        for (int i = 1; i <= number; i++) {
            gd.setColor(new Color(RANDOM_GENERATOR.nextInt(255), RANDOM_GENERATOR.nextInt(255), RANDOM_GENERATOR.nextInt(255)));
            //圆直径（10~20）
            int diameter = RANDOM_GENERATOR.nextInt(10) + 10;
            //防止圆超出边界
            int xStart = RANDOM_GENERATOR.nextInt(IMG_WIDTH - diameter);
            int yStart = RANDOM_GENERATOR.nextInt(IMG_HEIGHT - diameter);
            gd.drawOval(xStart, yStart, diameter, diameter);
        }
    }

    /**
     * 画矩形干扰图
     *
     * @param gd     Graphics2D 对象
     * @param number 画矩数量
     */
    private static void drawInterfereRect(Graphics2D gd, int number) {
        for (int i = 1; i <= number; i++) {
            gd.setColor(new Color(RANDOM_GENERATOR.nextInt(255), RANDOM_GENERATOR.nextInt(255), RANDOM_GENERATOR.nextInt(255)));
            //矩形形宽设置（10~20）
            int rectWidth = RANDOM_GENERATOR.nextInt(10) + 10;
            //矩形形长设置（10~15）
            int rectHeight = RANDOM_GENERATOR.nextInt(10) + 5;
            //防止圆超出边界
            int xStart = RANDOM_GENERATOR.nextInt(IMG_WIDTH - rectWidth);
            int yStart = RANDOM_GENERATOR.nextInt(IMG_HEIGHT - rectHeight);
            gd.drawRect(xStart, yStart, rectWidth, rectHeight);
        }
    }

    /**
     * 保存图片到指定的输出流
     *
     * @param image image流
     * @param out   输出流
     */
    public static void output(BufferedImage image, OutputStream out)
            throws IOException {
        ImageIO.write(image, "JPEG", out);
    }

    //========================================图片二维码=========================================

    /**
     * 二维码尺寸
     */
    private static final int QR_CODE_SIZE = 300;
    /**
     * 内嵌LOGO宽度
     */
    private static final int LOGO_WIDTH = 65;
    /**
     * 内嵌LOGO高度
     */
    private static final int LOGO_HEIGHT = 65;

    /**
     * 创建一个二维码图片
     *
     * @param content      二维码内容
     * @param imgPath      二维码中间内嵌图片位置（不需要的时候传空）
     * @param needCompress 是否需要压缩
     * @return 二维码图片流
     * @throws IOException     输入输出异常
     * @throws WriterException 输出异常
     */
    public static BufferedImage createQrCode(String content, String imgPath,
                                      boolean needCompress) throws IOException, WriterException {
        EnumMap<EncodeHintType, Object> hints = new EnumMap<>(EncodeHintType.class);
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        hints.put(EncodeHintType.CHARACTER_SET, StandardCharsets.UTF_8);
        hints.put(EncodeHintType.MARGIN, 1);
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content,
                BarcodeFormat.QR_CODE, QR_CODE_SIZE, QR_CODE_SIZE, hints);
        int width = bitMatrix.getWidth();
        int height = bitMatrix.getHeight();
        BufferedImage image = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000
                        : 0xFFFFFFFF);
            }
        }
        if (imgPath == null || "".equals(imgPath)) {
            return image;
        }
        // 插入图片
        insertImage(image, imgPath, needCompress);
        return image;
    }

    /**
     * 在二维码中间插入图片
     *
     * @param source       二维码图片
     * @param imgPath      LOGO图片地址
     * @param needCompress 是否压缩
     * @throws IOException 输入输出异常
     */
    private static void insertImage(BufferedImage source, String imgPath,
                                    boolean needCompress) throws IOException {
        File file = new File(imgPath);
        if (!file.exists()) {
            log.error("{}该文件不存在！", imgPath);
            return;
        }
        Image src = ImageIO.read(new File(imgPath));
        int width = src.getWidth(null);
        int height = src.getHeight(null);
        // 压缩LOGO
        if (needCompress) {
            if (width > LOGO_WIDTH) {
                width = LOGO_WIDTH;
            }
            if (height > LOGO_HEIGHT) {
                height = LOGO_HEIGHT;
            }
            Image image = src.getScaledInstance(width, height,
                    Image.SCALE_SMOOTH);
            BufferedImage tag = new BufferedImage(width, height,
                    BufferedImage.TYPE_INT_RGB);
            Graphics g = tag.getGraphics();
            // 绘制缩小后的图
            g.drawImage(image, 0, 0, null);
            g.dispose();
            src = image;
        }
        // 插入LOGO
        Graphics2D graph = source.createGraphics();
        int x = (QR_CODE_SIZE - width) / 2;
        int y = (QR_CODE_SIZE - height) / 2;
        graph.drawImage(src, x, y, width, height, null);
        Shape shape = new RoundRectangle2D.Float(x, y, width, width, 6, 6);
        graph.setStroke(new BasicStroke(3f));
        graph.draw(shape);
        graph.dispose();
    }

    private GenerationCodeUtils() {
        throw new IllegalStateException("GenerationCode Utility class");
    }
}
