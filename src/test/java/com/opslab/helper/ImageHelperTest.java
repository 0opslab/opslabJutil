package com.opslab.helper;

import com.opslab.bean.ResultBean;
import com.opslab.util.JacksonUtil;
import com.opslab.helper.RandomHelper;
import com.opslab.util.TestUtil;
import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class ImageHelperTest {

    private String file = TestUtil.path + "/image";

    private String path_temp = TestUtil.path + "/temp/image";

    @Test
    public void testIsImage(){
        System.out.println(ImageHelper.isImage(file+"/tgepng"));
    }

    @Test
    public void tesCaptch(){
        int width = 200;
        int height = 40;
        ResultBean resultBean = ImageHelper.pngCaptcha(5, width, height, path_temp + RandomHelper.UUID() + ".png");
        System.out.println(JacksonUtil.toJson(resultBean));

        ResultBean resultBean1 = ImageHelper.pngCaptcha(6, width, height, path_temp + RandomHelper.UUID() + ".png");
        System.out.println(JacksonUtil.toJson(resultBean1));


        ResultBean resultBean2 = ImageHelper.pngCaptchaBase64(6, width, height);
        if(resultBean2.isSuccess()){
            System.out.println("<img src='"+resultBean2.getData().toString()+"'/>");
        }


        ResultBean resultBean3 = ImageHelper.gifCaptch(5, width, height, path_temp + RandomHelper.UUID() + ".gif");
        System.out.println(JacksonUtil.toJson(resultBean3));

        ResultBean resultBean4 = ImageHelper.gifCaptch(5, width, height, path_temp + RandomHelper.UUID() + ".gif");
        System.out.println(JacksonUtil.toJson(resultBean4));

        ResultBean resultBean5 = ImageHelper.gifCaptchBase64(6,  width,height);
        if(resultBean5.isSuccess()){
            System.out.println("<img src='"+resultBean5.getData().toString()+"'/>");
        }

    }

}