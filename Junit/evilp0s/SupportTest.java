package evilp0s;

import evilp0s.DateUtil;
import org.junit.After;
import org.junit.Before;

import java.util.Date;

/**
 * @Description:Junit测试类
 */
public class SupportTest {
    private Date start;

    @Before
    public void before() {
        start = new Date();
    }

    @After
    public void after() {
        Date end = new Date();
        System.out.println("函数执行完成->耗时" + DateUtil.Subtract(start, end) + "[" + DateUtil.DateTime(start) + "/" + DateUtil.DateTime(end) + "]");
    }
}
