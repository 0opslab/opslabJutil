package JDK.String;


import junit.framework.TestCase;

public class StringTest extends TestCase {

    public void testsubString() {
        //测试JDK字符串的截取
        System.out.println("中文字符串测试".substring(1, 3));
        System.out.println("中a文b字c符d串e测f试g".substring(3, 7));
    }

    public void testStringLen() {
        //测试JDK字符串的长度函数
        System.out.println("中文字符串测试".length());
    }
}
