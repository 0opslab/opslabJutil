package evilp0s.Bean;

import evilp0s.PrintUtil;
import evilp0s.StringUtil;
import junit.framework.TestCase;
import model.BusinessLog;
import model.Log;
import model.Log2;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

public class BeanUtilTest extends TestCase {

    @Test
    public void testHasProperties() {
        //全局执行添加一次
        BeanUtil.add(BusinessLog.class);

        BusinessLog bean = new BusinessLog();

        PrintUtil.print("Bean类是否有operationName属性:" + BeanUtil.hasProperty(bean, "operationName"));

        PrintUtil.print("Bean类中是否存在operationName属性(判断将忽略大小写):" + BeanUtil.hasPropertyIgnoreCase(bean, "OperationName"));

        PrintUtil.print("Bean类中是否定义属性logId:" + BeanUtil.hasDeclaredProperty(bean, "logId"));

        PrintUtil.print("Bean类中是否存在属性(对属性命执行自定义的过滤函数后比较)" + BeanUtil.hasPropertyFilter(bean, "operationType", new PropertyFilter() {
                    @Override
                    public String Properties(String pro) {
                        //忽略属性字段中"_" 并安装小写比较
                        return StringUtil.remove(pro, "_").toLowerCase();
                    }
                }));

    }


    @Test
    public void testGetProperties() throws InvocationTargetException, IllegalAccessException {
        BeanUtil.add(BusinessLog.class);

        BusinessLog bean = new BusinessLog();

        bean.setOperationName("Test BeanUtil getProperties Method");
        PrintUtil.println("获取属性:" + BeanUtil.getProperty(bean, "operationName"));
        PrintUtil.println("获取属性(忽略大小写):" + BeanUtil.getPropertyIgnoreCase(bean, "operationname"));
        PrintUtil.println("获取属性(使用自定的过滤函数):" + BeanUtil.getPropertyFilter(bean, "operation_Name", new PropertyFilter() {
            @Override
            public String Properties(String pro) {
                return StringUtil.remove(pro, "_").toLowerCase();
            }
        }));
    }

    @Test
    public void testSetProperties() throws InvocationTargetException, IllegalAccessException {
        BeanUtil.add(BusinessLog.class);
        BusinessLog bean = new BusinessLog();
        BeanUtil.setProperty(bean, "operationName", "Properties's value1");
        PrintUtil.println(bean);
        BeanUtil.setPropertyIgnoreCase(bean, "operationname", "Properties's value2");
        PrintUtil.println(bean);
        BeanUtil.setPropertyFilter(bean, "operation_Name", "Properties's value3", new PropertyFilter() {
            @Override
            public String Properties(String pro) {
                return StringUtil.remove(pro, "_").toLowerCase();
            }
        });
        PrintUtil.println(bean);
    }

    @Test
    public void testCopyProperty() throws InvocationTargetException, IllegalAccessException {
        BeanUtil.add(BusinessLog.class);
        BeanUtil.add(Log.class);
        BeanUtil.add(Log2.class);

        BusinessLog bean1 = new BusinessLog();
        bean1.setOperationName("operationName test");
        bean1.setOperation_type("operationName type");

        Log bean2 = new Log();

        BeanUtil.copyProperty(bean1, bean2, new String[]{"operationName"});
        PrintUtil.println(bean2);

        Log bean3 = new Log();
        BeanUtil.copyProperties(bean1, bean3);
        PrintUtil.println(bean3);


        Log2 bean4 = new Log2();
        BeanUtil.copyPropertiesIgnoreCase(bean1,bean4);
        PrintUtil.println(bean4);
    }
}