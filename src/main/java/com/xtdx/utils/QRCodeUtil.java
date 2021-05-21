package com.xtdx.utils;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.img.ImgUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;
import cn.hutool.extra.qrcode.QrConfig;
import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;

/**
 * @program: online-voting-system
 * @description: 二维码生成
 * @author: LEO
 * @create: 2021-05-22 01:26
 **/
public class QRCodeUtil {

    /**
     * 生成二维码
     *
     * @param text 储存内容
     * @param width  宽度
     * @param height  高度
     * @return Base64编码
     */
    public static String getQRCodeBase64(String text, int width, int height) {
        QrConfig config = new QrConfig(width, height);
        // 设置边距，既二维码和背景之间的边距
        config.setMargin(1);
        // 设置前景色，既二维码颜色（青色）
        config.setForeColor(Color.BLACK.getRGB());
        // 设置背景色（灰色）
        config.setBackColor(Color.WHITE.getRGB());
        //
        config.setCharset(Charset.defaultCharset());
        // 生成二维码到文件，也可以到流
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        QrCodeUtil.generate(text, config, ImgUtil.IMAGE_TYPE_PNG, outputStream);
        byte[] pngData = outputStream.toByteArray();
        // 这个前缀，可前端加，可后端加，不加的话，不能识别为图片
        return "data:image/png;base64," + Base64.encode(pngData);
    }


    public static String readQRCodeBaseBufferedImage(File file){
        try
        {
            MultiFormatReader multiFormatReader=new MultiFormatReader();

            BufferedImage image= ImageIO.read(file);

            BinaryBitmap binaryBitmap=new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(image)));

            //二维码相关参数
            HashMap hints=new HashMap();
            hints.put(EncodeHintType.CHARACTER_SET, "utf-8");

            Result result=multiFormatReader.decode(binaryBitmap,hints);

            //输出解析结果
            System.out.println("解析结果："+result.toString());
            System.out.println("二维码格式:"+result.getBarcodeFormat());
            System.out.println("二维码文本内容："+result.getText());
            return result.getText();
        }
        catch (NotFoundException | IOException e)
        {
            e.printStackTrace();
        }
        return "";
    }

    public static void main(String[] args) {
        QrConfig config = new QrConfig(100, 100);
        // 设置边距，既二维码和背景之间的边距
        config.setMargin(3);
        // 设置前景色，既二维码颜色（青色）
        config.setForeColor(Color.CYAN.getRGB());
        // 设置背景色（灰色）
        config.setBackColor(Color.GRAY.getRGB());
        // 生成二维码到文件，也可以到流
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        QrCodeUtil.generate("wawaw", config, ImgUtil.IMAGE_TYPE_PNG, outputStream);
        byte[] pngData = outputStream.toByteArray();
        System.out.println(Base64.encode(pngData));
    }
}

