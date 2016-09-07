package com.opslab.util.image;

import java.util.Random;

/**
 * 生成图片验证码
 */
public final class ImageCaptcha {
    //指定图片的宽度
    private static int width =200;
    //指定图片的高度
    private static int height= 40;
    //指定所以的字符
    public static  String CHAR    = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static String getCHAR() {
        return CHAR;
    }

    public static void setCHAR(String CHAR) {
        ImageCaptcha.CHAR = CHAR;
    }

    public static int getWidth() {
        return width;
    }

    public static void setWidth(int width) {
        ImageCaptcha.width = width;
    }

    public static int getHeight() {
        return height;
    }

    public static void setHeight(int height) {
        ImageCaptcha.height = height;
    }

    /**
     * 随机指定长度的字符串
     * @param len
     * @return
     */
    private static String randomStr(int len){
        StringBuffer sb     = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < len; i++) {
            sb.append(CHAR.charAt(random.nextInt(CHAR.length())));
        }
        return sb.toString();

    }

    /**
     * 生产一张png格式的验证图片在指定的位置
     * @param strlen 验证码长度
     * @param file 文件位置
     * @return 是否成功
     */
    public static String pngCaptcha(int strlen,String file){
        String random = randomStr(strlen);
        if(CaptchaUtil.pngCaptcha(random,width,height,file)){
            return random;
        }
        return "";
    }

    public static String gifCaptch(int strlen,String file){
        String random = randomStr(strlen);
        if(CaptchaUtil.gifCaptcha(random,width,height,file)){
            return random;
        }
        return "";
    }
}
