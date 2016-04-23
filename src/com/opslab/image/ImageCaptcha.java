package com.opslab.image;

/**
 * 生成图片验证码
 */
public class ImageCaptcha {

    private static int width =200;
    private static int height= 40;

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
     * 生产一张png格式的验证图片在指定的位置
     * @param strlen 验证码长度
     * @param file 文件位置
     * @return 是否成功
     */
    public static String pngCaptcha(int strlen,String file){
        return "";
    }

    public static String gifCaptch(int strlen,String file){
        return "";
    }
}
