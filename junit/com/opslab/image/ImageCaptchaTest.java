package com.opslab.image;

import com.opslab.RandomUtil;
import junit.framework.TestCase;

/**
 * 测试验证码生成
 */
public class ImageCaptchaTest extends TestCase {

    private String path_temp =System.getProperty("user.dir") + "/Junit/Resource/image/temp/";

    public void testPngCaptcha() throws Exception {
        System.out.println(ImageCaptcha.pngCaptcha(5, path_temp+ RandomUtil.UUID()+".png"));
        System.out.println(ImageCaptcha.pngCaptcha(6, path_temp+ RandomUtil.UUID()+".png"));
    }

    public void testGifCaptch() throws Exception {
        System.out.println(ImageCaptcha.gifCaptch(5, path_temp + RandomUtil.UUID() + ".gif"));
        System.out.println(ImageCaptcha.gifCaptch(6, path_temp + RandomUtil.UUID() + ".gif"));
    }
}