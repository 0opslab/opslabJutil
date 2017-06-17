package com.opslab.util.bean;

import com.opslab.util.StringUtil;
import junit.framework.TestCase;
import com.opslab.temp.model.BusinessLog;
import com.opslab.temp.model.Log;
import com.opslab.temp.model.Log2;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

public class BeanUtilTest extends TestCase {

    @Test
    public void testHasProperties() {
        BusinessLog bean = new BusinessLog();

        //Bean类是否有operationName属性
        assertEquals(true, BeanUtil.hasProperty(bean, "operationName"));


        //Bean类中是否存在operationName属性(判断将忽略大小写)
        assertEquals(true, BeanUtil.hasPropertyIgnoreCase(bean, "OperationName"));

        //Bean类中是否定义属性logId
        assertEquals(false, BeanUtil.hasDeclaredProperty(bean, "logId"));

        //Bean类中是否存在属性(对属性命执行自定义的过滤函数后比较)
        assertEquals(true, BeanUtil.hasPropertyFilter(bean, "operationType", new PropertyFilter() {
            @Override
            public String Properties(String pro) {
                //忽略属性字段中"_" 并安装小写比较
                return StringUtil.remove(pro, "_").toLowerCase();
            }
        }));

    }


    @Test
    public void testGetProperties() throws InvocationTargetException, IllegalAccessException {
        String      value = "Test BeanUtil getProperties Method";
        BusinessLog bean  = new BusinessLog();
        bean.setOperationName(value);

        assertEquals(value, BeanUtil.getProperty(bean, "operationName"));

        //获取属性(忽略大小写):
        assertEquals(value, BeanUtil.getPropertyIgnoreCase(bean, "operationname"));

        //平静的获取属性
        assertEquals(value, BeanUtil.getPropertyPeaceful(bean, "operationName"));

        //获取属性(忽略大小写)平静:
        assertEquals(value, BeanUtil.getPropertyIgnoreCasePeaceful(bean, "operationname"));


        //获取属性(使用自定的过滤函数):
        assertEquals(value, BeanUtil.getPropertyFilter(bean, "operation_Name", new PropertyFilter() {
            @Override
            public String Properties(String pro) {
                return StringUtil.remove(pro, "_").toLowerCase();
            }
        }));

        //获取属性(使用自定的过滤函数):
        assertEquals(value, BeanUtil.getPropertyFilterPeaceful(bean, "operation_Name", new PropertyFilter() {
            @Override
            public String Properties(String pro) {
                return StringUtil.remove(pro, "_").toLowerCase();
            }
        }));


    }

    @Test
    public void testSetProperties() throws InvocationTargetException, IllegalAccessException {
        String value ="Properties's value1";
        BusinessLog bean = new BusinessLog();

        BeanUtil.setProperty(bean,"operationName", value);
        assertEquals(value, bean.getOperationName());

        BeanUtil.setPropertyPeaceful(bean, "operationName", value + "2");
        assertEquals(value + "2", bean.getOperationName());


        BeanUtil.setPropertyIgnoreCase(bean, "operationname", value + "3");
        assertEquals(value + "3", bean.getOperationName());

        BeanUtil.setPropertyIgnoreCasePeaceful(bean, "operationname", value + "5");
        assertEquals(value + "5", bean.getOperationName());

        BeanUtil.setPropertyFilter(bean, "operation_Name", value + "4", new PropertyFilter() {
            @Override
            public String Properties(String pro) {
                return StringUtil.remove(pro, "_").toLowerCase();
            }
        });
        assertEquals(value + "4", bean.getOperationName());

        BeanUtil.setPropertyFilterPeaceful(bean, "operation_Name", value + "6", new PropertyFilter() {
            @Override
            public String Properties(String pro) {
                return StringUtil.remove(pro, "_").toLowerCase();
            }
        });
        assertEquals(value + "6", bean.getOperationName());
    }

    @Test
    public void testCopyProperty() throws InvocationTargetException, IllegalAccessException {

        BusinessLog bean1 = new BusinessLog();
        bean1.setOperationName("operationName test");
        bean1.setOperation_type("operationName type");

        Log bean2 = new Log();

        BeanUtil.copyProperty(bean1, bean2, new String[]{"operationName"});
        System.out.println(bean2);

        Log bean3 = new Log();
        BeanUtil.copyProperties(bean1, bean3);
        System.out.println(bean3);
        BeanUtil.copyPropertiesPeaceful(bean1, bean3);
        System.out.println(bean3);


        Log2 bean4 = new Log2();
        BeanUtil.copyPropertiesIgnoreCase(bean1, bean4);
        System.out.println(bean4);
        BeanUtil.copyPropertiesIgnoreCasePeaceful(bean1, bean4);
        System.out.println(bean4);
        Log bean5 = new Log();
        BeanUtil.copyPropertyPeaceful(bean1, bean5, new String[]{"operationName"});
        System.out.println(bean4);
    }

    @Test
    public void testCopyProperties() throws InvocationTargetException, IllegalAccessException {
        BusinessLog bean1 = new BusinessLog();
        bean1.setOperationName("operationName test");
        bean1.setOperation_type("operationName type");
        bean1.setLogType("logTypevalue");

        Log2 bean2 = new Log2();

        System.out.println("复制前:" + bean1);
        System.out.println("复制前:" + bean2);
        BeanUtil.copyProperties(bean1, bean2, new PropertyFilter() {
            @Override
            public String Properties(String pro) {
                return StringUtil.remove(pro, "_").toLowerCase().replaceAll("yy", "ty");
            }
        });
        BeanUtil.copyPropertiesPeaceful(bean1, bean2, new PropertyFilter() {
            @Override
            public String Properties(String pro) {
                return StringUtil.remove(pro, "_").toLowerCase().replaceAll("yy", "ty");
            }
        });
        System.out.println("复制后:" + bean1);
        System.out.println("复制后:" + bean2);
    }
}