package com.opslab.util.image;

import com.opslab.helper.RandomHelper;
import junit.framework.TestCase;
import com.opslab.util.TestUtil;
import org.junit.Ignore;

/**
 * 测试验证码生成
 */
@Ignore
public class ImageCaptchaTest extends TestCase {

    private String path_temp = TestUtil.path + "/temp/image";

    public void testPngCaptcha() throws Exception {
        System.out.println(ImageCaptcha.pngCaptcha(5, path_temp + RandomHelper.UUID() + ".png"));
        System.out.println(ImageCaptcha.pngCaptcha(6, path_temp + RandomHelper.UUID() + ".png"));

        System.out.println("<img src='"+ImageCaptcha.pngCaptchaBase64(6)+"'/>");

        System.out.println("<img src='"+ImageCaptcha.gifCaptchBase64(6)+"'/>");
    }

    public void testGifCaptch() throws Exception {
        System.out.println(ImageCaptcha.gifCaptch(5, path_temp + RandomHelper.UUID() + ".gif"));
        System.out.println(ImageCaptcha.gifCaptch(6, path_temp + RandomHelper.UUID() + ".gif"));
    }


}