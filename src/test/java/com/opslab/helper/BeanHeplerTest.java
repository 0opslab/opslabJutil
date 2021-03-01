package com.opslab.helper;

import com.opslab.functions.PropertyFilter;
import com.opslab.util.StringUtil;
import junit.framework.TestCase;
import com.opslab.temp.model.TessModelBusinessLog;
import com.opslab.temp.model.TessModelLog;
import com.opslab.temp.model.TessModelLog2;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

public class BeanHeplerTest extends TestCase {

    @Test
    public void testHasProperties() {
        TessModelBusinessLog bean = new TessModelBusinessLog();

        //Bean类是否有operationName属性
        assertEquals(true, BeanHepler.hasProperty(bean, "operationName"));


        //Bean类中是否存在operationName属性(判断将忽略大小写)
        assertEquals(true, BeanHepler.hasPropertyIgnoreCase(bean, "OperationName"));

        //Bean类中是否定义属性logId
        assertEquals(false, BeanHepler.hasDeclaredProperty(bean, "logId"));

        //Bean类中是否存在属性(对属性命执行自定义的过滤函数后比较)
        assertEquals(true, BeanHepler.hasPropertyFilter(bean, "operationType", new PropertyFilter() {
            @Override
            public String Properties(String pro) {
                //忽略属性字段中"_" 并安装小写比较
                return StringUtil.remove(pro, "_").toLowerCase();
            }
        }));

    }


    @Test
    public void testGetProperties() throws InvocationTargetException, IllegalAccessException {
        String value = "Test BeanHepler getProperties Method";
        TessModelBusinessLog bean = new TessModelBusinessLog();
        bean.setOperationName(value);

        assertEquals(value, BeanHepler.getProperty(bean, "operationName"));

        //获取属性(忽略大小写):
        assertEquals(value, BeanHepler.getPropertyIgnoreCase(bean, "operationname"));

        //平静的获取属性
        assertEquals(value, BeanHepler.getPropertyPeaceful(bean, "operationName"));

        //获取属性(忽略大小写)平静:
        assertEquals(value, BeanHepler.getPropertyIgnoreCasePeaceful(bean, "operationname"));


        //获取属性(使用自定的过滤函数):
        assertEquals(value, BeanHepler.getPropertyFilter(bean, "operation_Name", new PropertyFilter() {
            @Override
            public String Properties(String pro) {
                return StringUtil.remove(pro, "_").toLowerCase();
            }
        }));

        //获取属性(使用自定的过滤函数):
        assertEquals(value, BeanHepler.getPropertyFilterPeaceful(bean, "operation_Name", new PropertyFilter() {
            @Override
            public String Properties(String pro) {
                return StringUtil.remove(pro, "_").toLowerCase();
            }
        }));


    }

    @Test
    public void testSetProperties() throws InvocationTargetException, IllegalAccessException {
        String value = "Properties's value1";
        TessModelBusinessLog bean = new TessModelBusinessLog();

        BeanHepler.setProperty(bean, "operationName", value);
        assertEquals(value, bean.getOperationName());

        BeanHepler.setPropertyPeaceful(bean, "operationName", value + "2");
        assertEquals(value + "2", bean.getOperationName());


        BeanHepler.setPropertyIgnoreCase(bean, "operationname", value + "3");
        assertEquals(value + "3", bean.getOperationName());

        BeanHepler.setPropertyIgnoreCasePeaceful(bean, "operationname", value + "5");
        assertEquals(value + "5", bean.getOperationName());

        BeanHepler.setPropertyFilter(bean, "operation_Name", value + "4", new PropertyFilter() {
            @Override
            public String Properties(String pro) {
                return StringUtil.remove(pro, "_").toLowerCase();
            }
        });
        assertEquals(value + "4", bean.getOperationName());

        BeanHepler.setPropertyFilterPeaceful(bean, "operation_Name", value + "6", new PropertyFilter() {
            @Override
            public String Properties(String pro) {
                return StringUtil.remove(pro, "_").toLowerCase();
            }
        });
        assertEquals(value + "6", bean.getOperationName());
    }

    @Test
    public void testCopyProperty() throws InvocationTargetException, IllegalAccessException {

        TessModelBusinessLog bean1 = new TessModelBusinessLog();
        bean1.setOperationName("operationName test");
        bean1.setOperation_type("operationName type");

        TessModelLog bean2 = new TessModelLog();

        BeanHepler.copyProperty(bean1, bean2, new String[]{"operationName"});
        System.out.println(bean2);

        TessModelLog bean3 = new TessModelLog();
        BeanHepler.copyProperties(bean1, bean3);
        System.out.println(bean3);
        BeanHepler.copyPropertiesPeaceful(bean1, bean3);
        System.out.println(bean3);


        TessModelLog2 bean4 = new TessModelLog2();
        BeanHepler.copyPropertiesIgnoreCase(bean1, bean4);
        System.out.println(bean4);
        BeanHepler.copyPropertiesIgnoreCasePeaceful(bean1, bean4);
        System.out.println(bean4);
        TessModelLog bean5 = new TessModelLog();
        BeanHepler.copyPropertyPeaceful(bean1, bean5, new String[]{"operationName"});
        System.out.println(bean4);
    }

    @Test
    public void testCopyProperties() throws InvocationTargetException, IllegalAccessException {
        TessModelBusinessLog bean1 = new TessModelBusinessLog();
        bean1.setOperationName("operationName test");
        bean1.setOperation_type("operationName type");
        bean1.setLogType("logTypevalue");

        TessModelLog2 bean2 = new TessModelLog2();

        System.out.println("复制前:" + bean1);
        System.out.println("复制前:" + bean2);
        BeanHepler.copyProperties(bean1, bean2, new PropertyFilter() {
            @Override
            public String Properties(String pro) {
                return StringUtil.remove(pro, "_").toLowerCase().replaceAll("yy", "ty");
            }
        });
        BeanHepler.copyPropertiesPeaceful(bean1, bean2, new PropertyFilter() {
            @Override
            public String Properties(String pro) {
                return StringUtil.remove(pro, "_").toLowerCase().replaceAll("yy", "ty");
            }
        });
        System.out.println("复制后:" + bean1);
        System.out.println("复制后:" + bean2);
    }
}