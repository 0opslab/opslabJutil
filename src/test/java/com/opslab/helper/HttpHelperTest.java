package com.opslab.helper;

import com.opslab.Opslab;
import org.junit.Test;

public class HttpHelperTest {
    @Test
    public void testGet(){
        String http ="https://www.baidu.com/";
        String body = HttpHelper.sendGet(http);
        System.out.println(body);


        String body1 = HttpHelper.sendGetSSL(http, true);
        System.out.println(body1);
    }

    @Test
    public void testPost(){
        String http ="https://www.runoob.com/php/php-tutorial.html";
        String body = HttpHelper.sendPost(http, Opslab.STR_EMPTY);
        System.out.println(body);


        String body1 = HttpHelper.sendPostSSL(http, Opslab.STR_EMPTY,true);
        System.out.println(body1);
    }
}