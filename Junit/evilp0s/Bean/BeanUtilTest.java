package evilp0s.Bean;

import evilp0s.PrintUtil;
import evilp0s.StringUtil;
import junit.framework.TestCase;
import model.BusinessLog;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

public class BeanUtilTest extends TestCase {

    @Test
    public void testHasProperties(){
        //全局执行添加一次
        BeanUtil.add(BusinessLog.class);

        BusinessLog bean = new BusinessLog();

        PrintUtil.print("Bean类是否有operationName属性:"+BeanUtil.hasProperty(bean,"operationName"));

        PrintUtil.print("Bean类中是否存在operationName属性(判断将忽略大小写):"+
                BeanUtil.hasPropertyIgnoreCase(bean,"OperationName"));

        PrintUtil.print("Bean类中是否定义属性logId:"+BeanUtil.hasDeclaredProperty(bean,"logId"));

        PrintUtil.print(
                "Bean类中是否存在属性(对属性命执行自定义的过滤函数后比较)"+
                BeanUtil.hasPropertyFilter(bean, "operationType", new PropertyFilter() {
                    @Override
                    public String Properties(String pro) {
                        //忽略属性字段中"_" 并安装小写比较
                        return StringUtil.remove(pro, "_").toLowerCase();
                    }
                })
        );

    }


    @Test
    public void testGetProperties() throws InvocationTargetException, IllegalAccessException {
        BeanUtil.add(BusinessLog.class);

        BusinessLog bean = new BusinessLog();

        bean.setOperationName("Test BeanUtil getProperties Method");
        PrintUtil.println("获取属性:"+BeanUtil.getProperty(bean,"operationName"));
    }
}