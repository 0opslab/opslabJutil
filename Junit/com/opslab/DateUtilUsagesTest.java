package com.opslab;


import org.junit.Test;

import java.io.File;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 对DateUtil的测试实例的引用
 */
public class DateUtilUsagesTest {
    @Test
    public void testListLine() throws ParseException {
        File file = new File(System.getProperty("user.dir") + "/Junit/Resource/time.txt");
        List<String> lines = FileUtil.lines(file);
        int count=0;
        int count1 = 0;
        for(String line :lines){
            String[] tt = line.split("/");
            count += DateUtil.subtimeBurst(tt[0], tt[1], "08:00-19:30");
            count1 += DateUtil.Subtract(tt[0], tt[1]);
        }
        System.out.println("sum1 > "+count/60/60);
        System.out.println("sum2 > "+count1/60/60);
        System.out.println("count > "+lines.size());

    }

    @Test
    public void testGruop()throws ParseException {
        File file = new File(System.getProperty("user.dir") + "/Junit/Resource/times.txt");
        List<String> lines = FileUtil.lines(file);
        Map<String,String> group = new HashMap<String,String>();
        for(String line:lines){
            String[] tt= line.split("/");
            if(group.get(tt[2]) != null){
                String value =group.get(tt[2]);
                int count = 0;
                int sum = 0;
                if(value != null && !"".equals(value)){
                    String[] ss = value.split(":");
                    sum = Integer.parseInt(ss[0]) ;
                    sum +=(int) DateUtil.subtimeBurst(tt[0], tt[1], "08:00-19:30");
                    count = Integer.parseInt(ss[1])+1;
                }
                group.put(tt[2],sum+":"+count);
            }else{
                int sum =(int) DateUtil.subtimeBurst(tt[0], tt[1], "08:00-19:30");
                int count =1;
                group.put(tt[2],sum+":"+count);
            }
        }
        for(Map.Entry<String,String> entry:group.entrySet()){
            String[] value = entry.getValue().split(":");
            int sum = Integer.parseInt(value[0])/60/60;
            int count = Integer.parseInt(value[1]);
            System.out.println(entry.getKey()+"=>"+sum+"/"+count);
        }
    }
}
