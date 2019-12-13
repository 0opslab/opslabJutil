package com.opslab.helper;

import com.opslab.bean.ResultBean;
import com.opslab.util.image.CaptchaUtil;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;

/**
 * 提供图片操作的常用方法
 */
public final class ImageHelper {
    /**
     * 判断呢是否是图片
     *
     * @param filePath 文件路径
     * @return
     */
    public static boolean isImage(String filePath) {
        File imageFile = new File(filePath);
        if (!imageFile.exists()) {
            return false;
        }
        Image img = null;
        try {
            img = ImageIO.read(imageFile);
            if (img == null || img.getWidth(null) <= 0 || img.getHeight(null) <= 0) {
                return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            img = null;
        }
    }

    /**
     * 生产一张png格式的验证图片在指定的位置
     *
     * @param strlen 验证码长度
     * @param width  图片宽带
     * @param height 图片高度
     * @param file   文件位置
     */
    public static ResultBean pngCaptcha(int strlen, int width, int height, String file) {
        String random = RandomHelper.string(strlen);
        if (CaptchaUtil.pngCaptcha(random, width, height, file)) {
            return new ResultBean(random,null,true);
        }
        return new ResultBean(random,null,false);
    }

    /**
     * 生成一张png格式的验证码图片以base64编码返回
     * @param strlen 验证码长度
     * @param width  图片宽带
     * @param height 图片高度
     * @return
     */
    public static ResultBean pngCaptchaBase64(int strlen,int width,int height) {
        String random = RandomHelper.string(strlen);
        String data = CaptchaUtil.pngCaptchaBase64(random, width, height);
        if(data != null){
            return new ResultBean(random,data,true);
        }
        return new ResultBean(random,null,false);
    }


    /**
     * 生成一张gif的验证码
     * @param strlen 验证码长度
     * @param width  图片宽带
     * @param height 图片高度
     * @param file   文件位置
     * @return
     */
    public static ResultBean gifCaptch(int strlen,int width,int height, String file) {
        String random = RandomHelper.string(strlen);
        if (CaptchaUtil.gifCaptcha(random, width, height, file)) {
            return new ResultBean(random,null,true);
        }
        return new ResultBean(random,null,false);
    }

    /**
     * 生成一张gif的验证码
     * @param strlen 验证码长度
     * @param width  图片宽带
     * @param height 图片高度
     * @return
     */
    public static ResultBean gifCaptchBase64(int strlen,int width,int height) {
        String random = RandomHelper.string(strlen);
        String data = CaptchaUtil.gifCaptchaBase64(random, width, height);
        if(data != null){
            return new ResultBean(random,data,true);
        }
        return new ResultBean(random,null,false);
    }
}
