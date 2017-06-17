package com.opslab.helper;

import com.opslab.functions.ObjectHandler;
import com.opslab.functions.ObjectProcess;
import com.opslab.util.FileUtil;
import com.opslab.util.TestUtil;
import org.junit.Test;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 测试集合对象的一些助手工具方法
 */
public class CollectionHelperTest {
    String path = TestUtil.path;

    @Test
    public void handler(){
        String file = path + "EnglishWrite.txt";
        List<String> lines = FileUtil.lines(new File(file));
        //如下所示，可以传递一个实现特定方法的类，以实现类似js中callback方法形式的编码风格
        CollectionHelper.handler(lines, new ObjectHandler<String>() {
            @Override
            public void handler(String line) {
                System.out.println("handler line:"+line);
            }
        });
    }

    @Test
    public void process(){
        String file = path + "EnglishWrite.txt";

        List<String> lines = FileUtil.lines(new File(file));

        //返回当长度大于15的行
        Set<String> result = new HashSet<String>();
        CollectionHelper.process(lines, result,new ObjectProcess<String, String>() {
            @Override
            public String process(String s) {
                if(s != null && s.length()> 15){
                    return s;
                }
                return null;
            }
        });

        System.out.println("====");
        CollectionHelper.handler(result, new ObjectHandler<String>() {
            @Override
            public void handler(String s) {
                System.out.println(s);
            }
        });
    }

}